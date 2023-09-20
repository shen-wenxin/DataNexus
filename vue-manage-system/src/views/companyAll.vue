<template>
	<div>
		<div class="container">
			<div class="handle-box">
				<el-select v-model="query.location" placeholder="注册地" class="handle-select mr10" @change="handleLocationChange">
					<el-option key="1" label="中国大陆" value="0"></el-option>
					<el-option key="2" label="中国香港" value="1"></el-option>
                    <el-option key="3" label="境外" value="2"></el-option>
				</el-select>
				<el-input v-model="query.unifiedSocialCredit" placeholder="统一社会信用编码" class="handle-input mr10"></el-input>
				<el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
                
				<el-button type="primary" :icon="Plus" @click="goToAddCompanyPage">新增</el-button>
                <el-button type="primary" @click="exportXlsxPage">导出为Excel</el-button>
			</div>
			<el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
				<el-table-column prop="companyCode" label="公司编码"></el-table-column>
				<el-table-column prop="companyName" label="公司全称"></el-table-column>
				<el-table-column prop="companyAbbreviation" label="公司简称"></el-table-column>
                <el-table-column prop="registeredLocation" label="公司注册地"></el-table-column>
				<el-table-column prop="companyType" label="公司类型"></el-table-column>
                <el-table-column prop="unifiedSocialCredit" label="统一社会信用编码"></el-table-column>

				<el-table-column label="操作" width="330" align="center">
					<template #default="scope">
                        
						<el-button text :icon="InfoFilled" @click="handleView(scope.$index, scope.row)" v-permiss="15">
							查看
						</el-button>
						<el-button text :icon="Edit" @click="handleEdit(scope.$index, scope.row)" v-permiss="16">
							修改
						</el-button>
                        <el-button text :icon="Delete" class="red" @click="handleDelete(scope.$index, scope.row)" v-permiss="16">
							删除
						</el-button>
					</template>
				</el-table-column>
			</el-table>
			<div class="pagination">
				<el-pagination
					background
					layout="total, prev, pager, next"
					:current-page="query.pageIndex"
					:page-size="query.pageSize"
					:total="pageTotal"
					@current-change="handlePageChange"
				></el-pagination>

                <el-button type="primary" @click="exportXlsx">导出所有公司数据</el-button>
			</div>
            
		</div>

		<!-- 编辑弹出框 -->
		<el-dialog title="编辑" v-model="editVisible" width="50%">
			<el-form label-width="150px">
				<el-form-item label="公司编码">
                    <template #label>
                        <span class="bold-label">公司编码</span>
                    </template>
                    <span>{{ form.companyCode }}</span>
                </el-form-item>
				<el-form-item label="公司全称">
					<el-input v-model="form.companyName" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="公司简称">
					<el-input v-model="form.companyAbbreviation" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="公司类型">
					<el-input v-model="form.companyType" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="公司邮箱">
					<el-input v-model="form.companyEmail" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="行业分类">
					<el-input v-model="form.industry" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="经营范围">
					<el-input v-model="form.businessScope" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="是否验证客户">
                    <el-switch v-model="form.verifiedCustomer" @change="isFormModified = true"></el-switch>
                </el-form-item>
                <el-form-item label="是否为深交所会员">
                    <el-switch v-model="form.szseMember" @change="handleSzseMemberChange"></el-switch>
                </el-form-item>
        
                <!-- 只有在 form.szseMember 为 true 时才显示深交所会员相关的表单项 -->
                <template v-if="form.szseMember">
                    <el-form-item label="深交所会员编码">
                        <el-input v-model="form.szseMemberCode" @input="isFormModified = true"></el-input>
                    </el-form-item>
                    <el-form-item label="深交所会员简称">
                        <el-input v-model="form.szseMemberAbbreviation" @input="isFormModified = true"></el-input>
                    </el-form-item>
                </template>
                <el-form-item label="客户状态">
					<el-input v-model="form.customerStatus" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="所在国家">
					<el-input v-model="form.country" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="所在省份">
					<el-input v-model="form.province" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="所在城市">
					<el-input v-model="form.city" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="主要联系人姓名">
					<el-input v-model="form.primaryContactName" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="主要联系人职务">
					<el-input v-model="form.primaryContactPosition" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="主要联系人电话">
					<el-input v-model="form.primaryContactPhone" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="主要联系人邮箱">
					<el-input v-model="form.primaryContactEmail" @input="isFormModified = true"></el-input>
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="editVisible = false">取 消</el-button>
					<el-button type="primary" @click="saveEdit">确 定</el-button>
				</span>
			</template>
		</el-dialog>
        <!-- 编辑弹出展示框 -->
       

        <!-- 编辑弹出框 -->
		<el-dialog title="展示详细信息" v-model="viewVisible" width="40%">
			 <!-- 展示表单 -->
            <el-form label-width="150px" :model="form">
                <el-form-item label="公司编码">
                    <template #label>
                        <span class="bold-label">公司编码</span>
                    </template>
                    <span>{{ form.companyCode }}</span>
                </el-form-item>
                <el-form-item label="公司全称">
                    <template #label>
                        <span class="bold-label">公司全称</span>
                    </template>
                    <span>{{ form.companyName }}</span>
                </el-form-item>
                    <el-form-item label="公司简称">
                    <template #label>
                        <span class="bold-label">公司简称</span>
                    </template>
                    <span>{{ form.companyAbbreviation }}</span>
                    </el-form-item>
                    <el-form-item label="公司注册地">
                    <template #label>
                        <span class="bold-label">公司注册地</span>
                    </template>
                    <span>{{ form.registeredLocation }}</span>
                    </el-form-item>
                    <el-form-item label="公司类型">
                    <template #label>
                        <span class="bold-label">公司类型</span>
                    </template>
                    <span>{{ form.companyType }}</span>
                    </el-form-item>
                    <el-form-item label="统一社会信用编码">
                    <template #label>
                        <span class="bold-label">统一社会信用编码</span>
                    </template>
                    <span>{{ form.unifiedSocialCredit }}</span>
                    </el-form-item>
                    <el-form-item label="公司详细注册地址">
                    <template #label>
                        <span class="bold-label">公司详细注册地址</span>
                    </template>
                    <span>{{ form.registeredAddress }}</span>
                    </el-form-item>
                    <el-form-item label="注册电话">
                    <template #label>
                        <span class="bold-label">注册电话</span>
                    </template>
                    <span>{{ form.registeredPhone }}</span>
                    </el-form-item>
                    <el-form-item label="公司邮箱">
                    <template #label>
                        <span class="bold-label">公司邮箱</span>
                    </template>
                    <span>{{ form.companyEmail }}</span>
                    </el-form-item>
                    <el-form-item label="成立时间">
                    <template #label>
                        <span class="bold-label">成立时间</span>
                    </template>
                    <span>{{ form.establishmentDate }}</span>
                    </el-form-item>
                    <el-form-item label="注册资本">
                    <template #label>
                        <span class="bold-label">注册资本</span>
                    </template>
                    <span>{{ form.registeredCapital }}</span>
                    </el-form-item>
                    <el-form-item label="法定代表人姓名">
                    <template #label>
                        <span class="bold-label">法定代表人姓名</span>
                    </template>
                    <span>{{ form.legalRepresentativeName }}</span>
                    </el-form-item>
                    <el-form-item label="法定代表人联系电话">
                    <template #label>
                        <span class="bold-label">法定代表人联系电话</span>
                    </template>
                    <span>{{ form.legalRepresentativePhone }}</span>
                    </el-form-item>
                    <el-form-item label="法定代表人身份证号">
                    <template #label>
                        <span class="bold-label">法定代表人身份证号</span>
                    </template>
                    <span>{{ form.legalRepresentativeId }}</span>
                    </el-form-item>
                    <el-form-item label="行业分类">
                    <template #label>
                        <span class="bold-label">行业分类</span>
                    </template>
                    <span>{{ form.industry }}</span>
                    </el-form-item>
                    <el-form-item label="经营范围">
                    <template #label>
                        <span class="bold-label">经营范围</span>
                    </template>
                    <span>{{ form.businessScope }}</span>
                    </el-form-item>
                    <el-form-item label="是否为已认证客户">
                    <template #label>
                        <span class="bold-label">是否为已认证客户</span>
                    </template>
                    <span>{{ form.verifiedCustomer }}</span>
                    </el-form-item>
                    <el-form-item label="是否为深交所会员">
                    <template #label>
                        <span class="bold-label">是否为深交所会员</span>
                    </template>
                    <span>{{ form.szseMember }}</span>
                    </el-form-item>
                    <el-form-item label="深交所会员编码">
                    <template #label>
                        <span class="bold-label">深交所会员编码</span>
                    </template>
                    <span>{{ form.szseMemberCode }}</span>
                    </el-form-item>
                    <el-form-item label="深交所会员简称">
                    <template #label>
                        <span class="bold-label">深交所会员简称</span>
                    </template>
                    <span>{{ form.szseMemberAbbreviation }}</span>
                    </el-form-item>
                    <el-form-item label="客户状态">
                    <template #label>
                        <span class="bold-label">客户状态</span>
                    </template>
                    <span>{{ form.customerStatus }}</span>
                    </el-form-item>
                    <el-form-item label="所在国家">
                    <template #label>
                        <span class="bold-label">所在国家</span>
                    </template>
                    <span>{{ form.country }}</span>
                    </el-form-item>
                    <el-form-item label="所在省份">
                    <template #label>
                        <span class="bold-label">所在省份</span>
                    </template>
                    <span>{{ form.province }}</span>
                    </el-form-item>
                    <el-form-item label="所在城市">
                    <template #label>
                        <span class="bold-label">所在城市</span>
                    </template>
                    <span>{{ form.city }}</span>
                    </el-form-item>
                    <el-form-item label="营业执照编号">
                    <template #label>
                        <span class="bold-label">营业执照编号</span>
                    </template>
                    <span>{{ form.businessLicenseNumber }}</span>
                    </el-form-item>
                    <el-form-item label="营业执照有效期">
                    <template #label>
                        <span class="bold-label">营业执照有效期</span>
                    </template>
                    <span>{{ form.businessLicenseExpiry }}</span>
                    </el-form-item>
                    <el-form-item label="主要联系人姓名">
                    <template #label>
                        <span class="bold-label">主要联系人姓名</span>
                    </template>
                    <span>{{ form.primaryContactName }}</span>
                    </el-form-item>
                    <el-form-item label="主要联系人职务">
                    <template #label>
                        <span class="bold-label">主要联系人职务</span>
                    </template>
                    <span>{{ form.primaryContactPosition }}</span>
                    </el-form-item>
                    <el-form-item label="主要联系人电话">
                    <template #label>
                        <span class="bold-label">主要联系人电话</span>
                    </template>
                    <span>{{ form.primaryContactPhone }}</span>
                    </el-form-item>
                    <el-form-item label="主要联系人邮箱">
                    <template #label>
                        <span class="bold-label">主要联系人邮箱</span>
                    </template>
                    <span>{{ form.primaryContactEmail }}</span>
                    </el-form-item>
            <!-- 添加其他字段的展示 -->
            </el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="viewVisible = false">确 定</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>

<script setup lang="ts" name="basetable">
import { ref, reactive } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { Delete, Edit, Search, Plus, InfoFilled } from '@element-plus/icons-vue';
import { deleteCompany, getAllCompanies, getCompaniesByRegisteredLocation, updateCompanyInfo, getCompanyByUnifiedSocialCredit, exportCompanies} from '../api/index';
import { dataType } from 'element-plus/es/components/table-v2/src/common';
import router from '../router';
import * as XLSX from 'xlsx';

interface TableItem {
  companyCode: string;
  companyName: string;
  companyAbbreviation?: string;
  registeredLocation: string;
  companyType?: string;
  unifiedSocialCredit: string;
  registeredAddress: string;
  registeredPhone: string;
  companyEmail: string;
  establishmentDate: string; // 根据实际情况选择合适的日期格式
  registeredCapital: number;
  legalRepresentativeName: string;
  legalRepresentativePhone: string;
  legalRepresentativeId: string;
  industry: string;
  businessScope: string;
  verifiedCustomer: boolean;
  szseMember: boolean;
  szseMemberCode: string;
  szseMemberAbbreviation: string;
  customerStatus: string;
  country: string;
  province: string;
  city: string;
  businessLicenseNumber: string;
  businessLicenseExpiry: string; // 根据实际情况选择合适的日期格式
  primaryContactName: string;
  primaryContactPosition: string;
  primaryContactPhone: string;
  primaryContactEmail: string;
}

const query = reactive({
	location: '',
	unifiedSocialCredit: '',
	pageIndex: 1,
	pageSize: 10,
});
const tableData = ref<TableItem[]>([]);
const pageTotal = ref(0);

const getCompanyData = (page: number, size: number) => {
    console.log(page, size);
    getAllCompanies(page, size)
    .then(response => {
      const data = response.data; 

      console.log('data:', data);
      tableData.value = data.content
      pageTotal.value = data.totalElements || 0; 
    })
    .catch(error => {
      // 处理请求错误
      console.error('请求错误:', error);
    });
}

const getCompanyDataByRegisteredLocation = (page: number, size: number, location: string) => {
    getCompaniesByRegisteredLocation(location, page, size)
    .then(response => {
        const data = response.data; 

        console.log('data:', data);
        tableData.value = data.content;
        pageTotal.value = data.totalElements || 0; 
    })
    .catch(error => {
        // 处理请求错误
        console.error('请求错误:', error);
    });

}

const exportXlsx = () => {
    exportCompanies()
    .then((response) => {
    console.log("Get in exportCompanies2")
    // 创建一个 Blob 对象，用于保存二进制数据
    const blob = new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
    // 创建一个临时 URL，用于生成下载链接
    const url = URL.createObjectURL(blob);
    // 创建一个 <a> 元素并模拟点击以下载文件
        const link = document.createElement('a');
        link.href = url;
        link.download = 'companies.xlsx';
        link.click();

        // 释放临时 URL 对象
        URL.revokeObjectURL(url);
    //   console.log('Excel 文件导出成功');
      // 在这里可以添加额外的成功处理逻辑，如果需要的话
    })
    .catch(error => {
      console.error('导出 Excel 文件时发生错误:', error);
      // 在这里可以添加错误处理逻辑，如果需要的话
    });

};
const list = [["Company Code",
                "Company Name",
                "Company Abbreviation",
                "Company Type",
                "Registered Location",
                "Unified Social Credit",
                "Registered Address",
                "Registered Phone",
                "Company Email",
                "Establishment Date",
                "Registered Capital",
                "Legal Representative Name",
                "Legal Representative Phone",
                "Legal Representative ID",
                "Industry",
                "Business Scope",
                "Verified Customer",
                "SZSE Member",
                "SZSE Member Code",
                "SZSE Member Abbreviation",
                "Customer Status",
                "Country",
                "Province",
                "City",
                "Business License Number",
                "Business License Expiry",
                "Primary Contact Name",
                "Primary Contact Position",
                "Primary Contact Phone",
                "Primary Contact Email"]];

const exportXlsxPage = () => {
  tableData.value.map((item: TableItem, i: number) => {
    const arr: any[] = [i + 1];
    arr.push(
      item.companyCode,
      item.companyName,
      item.companyAbbreviation || "", // 如果字段可能为空，请进行适当的处理
      item.companyType || "",
      item.registeredLocation,
      item.unifiedSocialCredit,
      item.registeredAddress,
      item.registeredPhone,
      item.companyEmail,
      item.establishmentDate,
      item.registeredCapital,
      item.legalRepresentativeName,
      item.legalRepresentativePhone,
      item.legalRepresentativeId,
      item.industry,
      item.businessScope,
      item.verifiedCustomer,
      item.szseMember,
      item.szseMemberCode,
      item.szseMemberAbbreviation,
      item.customerStatus,
      item.country,
      item.province,
      item.city,
      item.businessLicenseNumber,
      item.businessLicenseExpiry,
      item.primaryContactName,
      item.primaryContactPosition,
      item.primaryContactPhone,
      item.primaryContactEmail
    );
    list.push(arr);
  });
  let WorkSheet = XLSX.utils.aoa_to_sheet(list);
  let new_workbook = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(new_workbook, WorkSheet, "第一页");
  XLSX.writeFile(new_workbook, `数据.xlsx`);
};

const getCompanyDataByUnifiedSocialCredit = (code: string) => {
    getCompanyByUnifiedSocialCredit(code)
    .then(response => {
        const data = response.data; 
        tableData.value = data;
        pageTotal.value = data.totalElements || 0; 
    })
    .catch(error => {
        // 处理请求错误
        console.error('请求错误:', error);
    });
}

getCompanyData(query.pageIndex - 1, query.pageSize);


// 查询操作
const handleSearch = () => {

	query.pageIndex = 1;
	let code = query.unifiedSocialCredit;
    if(code == ""){
        query.location = "";
        getCompanyData(query.pageIndex - 1, query.pageSize);
    }else{
        getCompanyDataByUnifiedSocialCredit(code);
    }
    

};

// 地点下拉框改变事件处理程序
const handleLocationChange = (value: string) => {
  query.location = value;
  console.log("query.location: ", query.location)
  query.pageIndex = 1;
  getCompanyDataByRegisteredLocation((query.pageIndex - 1), query.pageSize, value);
};

// 分页导航
const handlePageChange = (val: number) => {
	query.pageIndex = val;
	getCompanyData((val - 1), query.pageSize);
};

// 删除操作
const handleDelete = (index: number, row: any) => {
    
    let code = row.companyCode
	// 二次确认删除
	ElMessageBox.confirm('确定要删除吗？', '提示', {
		type: 'warning'
	})
		.then(() => {
            deleteCompany(code).then(() => {
                ElMessage.success('删除成功');
                getCompanyData((query.pageIndex - 1), query.pageSize);
            }).catch((error) => {
                ElMessage.error('删除失败');
            })
		})
		.catch(() => {});
};


const editVisible = ref(false);
const isFormModified = ref(false);
const emailRegex = new RegExp(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/);
const phoneRegex = new RegExp(/^\d+$/);



let form = reactive({
    companyCode: '',
    companyName: '',
    companyAbbreviation: '',
    registeredLocation: '',
    companyType: '',
    unifiedSocialCredit: '',
    registeredAddress: '',
    registeredPhone: '',
    companyEmail: '',
    establishmentDate: '',
    registeredCapital: 0,
    legalRepresentativeName: '',
    legalRepresentativePhone: '',
    legalRepresentativeId: '',
    industry: '',
    businessScope: '',
    verifiedCustomer: false,
    szseMember: false,
    szseMemberCode: '',
    szseMemberAbbreviation: '',
    customerStatus: '',
    country: '',
    province: '',
    city: '',
    businessLicenseNumber: '',
    businessLicenseExpiry: '',
    primaryContactName: '',
    primaryContactPosition: '',
    primaryContactPhone: '',
    primaryContactEmail: ''
});



// 表格编辑时弹窗和保存
const handleSzseMemberChange = () => {
    isFormModified.value = true
    if (!form.szseMember) {
        form.szseMemberCode = '';
        form.szseMemberAbbreviation = '';
    }
};

let idx: number = -1;

const handleEdit = (index: number, row: any) => {
	idx = index;
    Object.assign(form, row); // 将行数据赋值给表单对象
    editVisible.value = true;
};

const viewVisible = ref(false);
const handleView = (index: number, row: any) => {
    idx = index;
    Object.assign(form, row); // 将行数据赋值给表单对象
    viewVisible.value = true;

};

const saveEdit = () => {
    if (isFormModified.value) {
        if(!emailRegex.test(form.companyEmail) || !emailRegex.test(form.primaryContactEmail)) {
            ElMessage.error(`请输入正确的邮箱地址`);
            return ;
        }

        if( !phoneRegex.test(form.primaryContactPhone)) {
            ElMessage.error(`请输入正确的电话`);
            return ;
        }
        // 将注册地转换为数字
        let registeredLocationValue = "0";
        if (form.registeredLocation === "中国香港"){
            registeredLocationValue = "1";
        }else if (form.registeredLocation === "境外") {
            registeredLocationValue = "2";
        } 
        form.registeredLocation = registeredLocationValue;


        updateCompanyInfo(form)
            .then(() => {
                isFormModified.value = false;
                ElMessage.success('更新成功');
                getCompanyData(query.pageIndex - 1, query.pageSize);
            })
            .catch(() => {
                ElMessage.error('更新失败');
            });
        // 标记重置为false
        isFormModified.value = false;

        getCompanyData(query.pageIndex - 1, query.pageSize);
        editVisible.value = false;
    }
	
};

const goToAddCompanyPage = () => {
  // 使用路由导航跳转到新增页面
    router.push('/CompanyAdd');
};

</script>

<style scoped>
.handle-box {
	margin-bottom: 20px;
}

.handle-select {
	width: 120px;
}

.handle-input {
	width: 300px;
}
.table {
	width: 100%;
	font-size: 14px;
}
.red {
	color: #F56C6C;
}
.mr10 {
	margin-right: 10px;
}
.table-td-thumb {
	display: block;
	margin: auto;
	width: 40px;
	height: 40px;
}

.bold-label {
    font-weight: bold;
}
</style>
