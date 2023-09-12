<template>
	<div>
		<div class="container">
			<div class="handle-box">
				<el-select v-model="query.address" placeholder="地址" class="handle-select mr10">
					<el-option key="1" label="广东省" value="广东省"></el-option>
					<el-option key="2" label="湖南省" value="湖南省"></el-option>
				</el-select>
				<el-input v-model="query.name" placeholder="用户名" class="handle-input mr10"></el-input>
				<el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
				<el-button type="primary" :icon="Plus">新增</el-button>
			</div>
			<el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
				<el-table-column prop="company_code" label="公司编码"></el-table-column>
				<el-table-column prop="company_name" label="公司全称"></el-table-column>
				<el-table-column prop="company_abbreviation" label="公司简称"></el-table-column>
                <el-table-column prop="registered_location" label="公司注册地"></el-table-column>
				<el-table-column prop="company_type" label="公司类型"></el-table-column>
                <el-table-column prop="registered_address" label="统一社会信用编码"></el-table-column>

				<el-table-column label="操作" width="220" align="center">
					<template #default="scope">
						<el-button text :icon="Edit" @click="handleEdit(scope.$index, scope.row)" v-permiss="15">
							查看
						</el-button>
						<el-button text :icon="Delete" class="red" @click="handleDelete(scope.$index)" v-permiss="16">
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
			</div>
		</div>

		<!-- 编辑弹出框 -->
		<el-dialog title="编辑" v-model="editVisible" width="30%">
			<el-form label-width="70px">
				<el-form-item label="用户名">
					<el-input v-model="form.name"></el-input>
				</el-form-item>
				<el-form-item label="地址">
					<el-input v-model="form.address"></el-input>
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="editVisible = false">取 消</el-button>
					<el-button type="primary" @click="saveEdit">确 定</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>

<script setup lang="ts" name="basetable">
import { ref, reactive } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { Delete, Edit, Search, Plus } from '@element-plus/icons-vue';
import { fetchData, getAllCompanies} from '../api/index';

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
  is_verified_customer: boolean;
  is_szse_member: boolean;
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
	address: '',
	name: '',
	pageIndex: 1,
	pageSize: 10,
    pageTotal: 0
});
const tableData = ref<TableItem[]>([]);
const pageTotal = ref(0);

const getCompanyData = (page: number, size: number) => {
    getAllCompanies(page, size)
    .then(response => {
      const data = response.data; // 获取响应数据

      // 在这里处理响应数据，例如更新前端的状态或进行其他操作
      console.log('data:', data);
      tableData.value = data.content
      query.pageTotal = data.totalElements || 0; // 更新总记录数
    })
    .catch(error => {
      // 处理请求错误
      console.error('请求错误:', error);
    });
}

getCompanyData(0, 10);


// 查询操作
const handleSearch = () => {
	query.pageIndex = 1;
	// getData();
};
// 分页导航
const handlePageChange = (val: number) => {
	query.pageIndex = val;
	// getData();
};

// 删除操作
const handleDelete = (index: number) => {
	// 二次确认删除
	ElMessageBox.confirm('确定要删除吗？', '提示', {
		type: 'warning'
	})
		.then(() => {
			ElMessage.success('删除成功');
			tableData.value.splice(index, 1);
		})
		.catch(() => {});
};

// 表格编辑时弹窗和保存
const editVisible = ref(false);
let form = reactive({
	name: '',
	address: ''
});
let idx: number = -1;
const handleEdit = (index: number, row: any) => {
	idx = index;
	form.name = row.name;
	form.address = row.address;
	editVisible.value = true;
};
const saveEdit = () => {
	editVisible.value = false;
	ElMessage.success(`修改第 ${idx + 1} 行成功`);
	// tableData.value[idx].name = form.name;
	// tableData.value[idx].address = form.address;
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
</style>
