<template>
	<div>
		<div class="container">
			<div class="handle-box">
				<el-select v-model="query.location" placeholder="注册地" class="handle-select mr10" @change="handleLocationChange">
					<el-option key="1" label="中国大陆" value="0"></el-option>
					<el-option key="2" label="中国香港" value="1"></el-option>
                    <el-option key="3" label="境外" value="2"></el-option>
				</el-select>
				<el-input v-model="query.unified_social_credit" placeholder="统一社会信用编码" class="handle-input mr10"></el-input>
				<el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
                
				<el-button type="primary" :icon="Plus" @click="goToAddCompanyPage">新增</el-button>
                <el-button type="primary" @click="exportXlsxPage">导出为Excel</el-button>
			</div>
			<el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
				<el-table-column prop="company_code" label="公司编码"></el-table-column>
				<el-table-column prop="company_name" label="公司全称"></el-table-column>
				<el-table-column prop="company_abbreviation" label="公司简称"></el-table-column>
                <el-table-column prop="registered_location" label="公司注册地"></el-table-column>
				<el-table-column prop="company_type" label="公司类型"></el-table-column>
                <el-table-column prop="unified_social_credit" label="统一社会信用编码"></el-table-column>

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
                    <span>{{ form.company_code }}</span>
                </el-form-item>
				<el-form-item label="公司全称">
					<el-input v-model="form.company_name" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="公司简称">
					<el-input v-model="form.company_abbreviation" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="公司类型">
					<el-input v-model="form.company_type" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="公司邮箱">
					<el-input v-model="form.company_email" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="行业分类">
					<el-input v-model="form.industry" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="经营范围">
					<el-input v-model="form.business_scope" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="是否验证客户">
                    <el-switch v-model="form.verified_customer" @change="isFormModified = true"></el-switch>
                </el-form-item>
                <el-form-item label="是否为深交所会员">
                    <el-switch v-model="form.szse_member" @change="handleSzseMemberChange"></el-switch>
                </el-form-item>
        
                <!-- 只有在 form.szse_member 为 true 时才显示深交所会员相关的表单项 -->
                <template v-if="form.szse_member">
                    <el-form-item label="深交所会员编码">
                        <el-input v-model="form.szse_member_code" @input="isFormModified = true"></el-input>
                    </el-form-item>
                    <el-form-item label="深交所会员简称">
                        <el-input v-model="form.szse_member_abbreviation" @input="isFormModified = true"></el-input>
                    </el-form-item>
                </template>
                <el-form-item label="客户状态">
					<el-input v-model="form.customer_status" @input="isFormModified = true"></el-input>
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
					<el-input v-model="form.primary_contact_name" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="主要联系人职务">
					<el-input v-model="form.primary_contact_position" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="主要联系人电话">
					<el-input v-model="form.primary_contact_phone" @input="isFormModified = true"></el-input>
				</el-form-item>
                <el-form-item label="主要联系人邮箱">
					<el-input v-model="form.primary_contact_email" @input="isFormModified = true"></el-input>
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
                    <span>{{ form.company_code }}</span>
                </el-form-item>
                <el-form-item label="公司全称">
                    <template #label>
                        <span class="bold-label">公司全称</span>
                    </template>
                    <span>{{ form.company_name }}</span>
                </el-form-item>
                    <el-form-item label="公司简称">
                    <template #label>
                        <span class="bold-label">公司简称</span>
                    </template>
                    <span>{{ form.company_abbreviation }}</span>
                    </el-form-item>
                    <el-form-item label="公司注册地">
                    <template #label>
                        <span class="bold-label">公司注册地</span>
                    </template>
                    <span>{{ form.registered_location }}</span>
                    </el-form-item>
                    <el-form-item label="公司类型">
                    <template #label>
                        <span class="bold-label">公司类型</span>
                    </template>
                    <span>{{ form.company_type }}</span>
                    </el-form-item>
                    <el-form-item label="统一社会信用编码">
                    <template #label>
                        <span class="bold-label">统一社会信用编码</span>
                    </template>
                    <span>{{ form.unified_social_credit }}</span>
                    </el-form-item>
                    <el-form-item label="公司详细注册地址">
                    <template #label>
                        <span class="bold-label">公司详细注册地址</span>
                    </template>
                    <span>{{ form.registered_address }}</span>
                    </el-form-item>
                    <el-form-item label="注册电话">
                    <template #label>
                        <span class="bold-label">注册电话</span>
                    </template>
                    <span>{{ form.registered_phone }}</span>
                    </el-form-item>
                    <el-form-item label="公司邮箱">
                    <template #label>
                        <span class="bold-label">公司邮箱</span>
                    </template>
                    <span>{{ form.company_email }}</span>
                    </el-form-item>
                    <el-form-item label="成立时间">
                    <template #label>
                        <span class="bold-label">成立时间</span>
                    </template>
                    <span>{{ form.establishment_date }}</span>
                    </el-form-item>
                    <el-form-item label="注册资本">
                    <template #label>
                        <span class="bold-label">注册资本</span>
                    </template>
                    <span>{{ form.registered_capital }}</span>
                    </el-form-item>
                    <el-form-item label="法定代表人姓名">
                    <template #label>
                        <span class="bold-label">法定代表人姓名</span>
                    </template>
                    <span>{{ form.legal_representative_name }}</span>
                    </el-form-item>
                    <el-form-item label="法定代表人联系电话">
                    <template #label>
                        <span class="bold-label">法定代表人联系电话</span>
                    </template>
                    <span>{{ form.legal_representative_phone }}</span>
                    </el-form-item>
                    <el-form-item label="法定代表人身份证号">
                    <template #label>
                        <span class="bold-label">法定代表人身份证号</span>
                    </template>
                    <span>{{ form.legal_representative_id }}</span>
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
                    <span>{{ form.business_scope }}</span>
                    </el-form-item>
                    <el-form-item label="是否为已认证客户">
                    <template #label>
                        <span class="bold-label">是否为已认证客户</span>
                    </template>
                    <span>{{ form.verified_customer }}</span>
                    </el-form-item>
                    <el-form-item label="是否为深交所会员">
                    <template #label>
                        <span class="bold-label">是否为深交所会员</span>
                    </template>
                    <span>{{ form.szse_member }}</span>
                    </el-form-item>
                    <el-form-item label="深交所会员编码">
                    <template #label>
                        <span class="bold-label">深交所会员编码</span>
                    </template>
                    <span>{{ form.szse_member_code }}</span>
                    </el-form-item>
                    <el-form-item label="深交所会员简称">
                    <template #label>
                        <span class="bold-label">深交所会员简称</span>
                    </template>
                    <span>{{ form.szse_member_abbreviation }}</span>
                    </el-form-item>
                    <el-form-item label="客户状态">
                    <template #label>
                        <span class="bold-label">客户状态</span>
                    </template>
                    <span>{{ form.customer_status }}</span>
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
                    <span>{{ form.business_license_number }}</span>
                    </el-form-item>
                    <el-form-item label="营业执照有效期">
                    <template #label>
                        <span class="bold-label">营业执照有效期</span>
                    </template>
                    <span>{{ form.business_license_expiry }}</span>
                    </el-form-item>
                    <el-form-item label="主要联系人姓名">
                    <template #label>
                        <span class="bold-label">主要联系人姓名</span>
                    </template>
                    <span>{{ form.primary_contact_name }}</span>
                    </el-form-item>
                    <el-form-item label="主要联系人职务">
                    <template #label>
                        <span class="bold-label">主要联系人职务</span>
                    </template>
                    <span>{{ form.primary_contact_position }}</span>
                    </el-form-item>
                    <el-form-item label="主要联系人电话">
                    <template #label>
                        <span class="bold-label">主要联系人电话</span>
                    </template>
                    <span>{{ form.primary_contact_phone }}</span>
                    </el-form-item>
                    <el-form-item label="主要联系人邮箱">
                    <template #label>
                        <span class="bold-label">主要联系人邮箱</span>
                    </template>
                    <span>{{ form.primary_contact_email }}</span>
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
  company_code: string;
  company_name: string;
  company_abbreviation?: string;
  registered_location: string;
  company_type?: string;
  unified_social_credit: string;
  registered_address: string;
  registered_phone: string;
  company_email: string;
  establishment_date: string; // 根据实际情况选择合适的日期格式
  registered_capital: number;
  legal_representative_name: string;
  legal_representative_phone: string;
  legal_representative_id: string;
  industry: string;
  business_scope: string;
  verified_customer: boolean;
  szse_member: boolean;
  szse_member_code: string;
  szse_member_abbreviation: string;
  customer_status: string;
  country: string;
  province: string;
  city: string;
  business_license_number: string;
  business_license_expiry: string; // 根据实际情况选择合适的日期格式
  primary_contact_name: string;
  primary_contact_position: string;
  primary_contact_phone: string;
  primary_contact_email: string;
}

const query = reactive({
	location: '',
	unified_social_credit: '',
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
      item.company_code,
      item.company_name,
      item.company_abbreviation || "", // 如果字段可能为空，请进行适当的处理
      item.company_type || "",
      item.registered_location,
      item.unified_social_credit,
      item.registered_address,
      item.registered_phone,
      item.company_email,
      item.establishment_date,
      item.registered_capital,
      item.legal_representative_name,
      item.legal_representative_phone,
      item.legal_representative_id,
      item.industry,
      item.business_scope,
      item.verified_customer,
      item.szse_member,
      item.szse_member_code,
      item.szse_member_abbreviation,
      item.customer_status,
      item.country,
      item.province,
      item.city,
      item.business_license_number,
      item.business_license_expiry,
      item.primary_contact_name,
      item.primary_contact_position,
      item.primary_contact_phone,
      item.primary_contact_email
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
	let code = query.unified_social_credit;
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
    
    let code = row.company_code
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
    company_code: '',
    company_name: '',
    company_abbreviation: '',
    registered_location: '',
    company_type: '',
    unified_social_credit: '',
    registered_address: '',
    registered_phone: '',
    company_email: '',
    establishment_date: '',
    registered_capital: 0,
    legal_representative_name: '',
    legal_representative_phone: '',
    legal_representative_id: '',
    industry: '',
    business_scope: '',
    verified_customer: false,
    szse_member: false,
    szse_member_code: '',
    szse_member_abbreviation: '',
    customer_status: '',
    country: '',
    province: '',
    city: '',
    business_license_number: '',
    business_license_expiry: '',
    primary_contact_name: '',
    primary_contact_position: '',
    primary_contact_phone: '',
    primary_contact_email: ''
});



// 表格编辑时弹窗和保存
const handleSzseMemberChange = () => {
    isFormModified.value = true
    if (!form.szse_member) {
        form.szse_member_code = '';
        form.szse_member_abbreviation = '';
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
        if(!emailRegex.test(form.company_email) || !emailRegex.test(form.primary_contact_email)) {
            ElMessage.error(`请输入正确的邮箱地址`);
            return ;
        }

        if( !phoneRegex.test(form.primary_contact_phone)) {
            ElMessage.error(`请输入正确的电话`);
            return ;
        }
        // 将注册地转换为数字
        let registeredLocationValue = "0";
        if (form.registered_location === "中国香港"){
            registeredLocationValue = "1";
        }else if (form.registered_location === "境外") {
            registeredLocationValue = "2";
        } 
        form.registered_location = registeredLocationValue;


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
