<template>
	<div>
		<div class="container">
			<div class="handle-box">
				<el-select v-model="query.type" placeholder="修改方式" class="handle-select mr10" @change="handleTypeChange">
					<el-option key="1" label="新增" value="0"></el-option>
					<el-option key="2" label="修改" value="1"></el-option>
                    <el-option key="3" label="删除" value="2"></el-option>
				</el-select>
				<el-input v-model="query.code" placeholder="公司编码" class="handle-input mr10"></el-input>
				<el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
                <el-button type="primary" @click="exportXlsxPage">导出为Excel</el-button>
			</div>
			<el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
				<el-table-column prop="recordId" label="ID" width="55" align="center"></el-table-column>
				<el-table-column prop="companyCode" label="公司编码"></el-table-column>
				<el-table-column prop="operationType" label="操作类型"></el-table-column>
                <el-table-column prop="operationTime" label="时间"></el-table-column>
				<el-table-column prop="operator" label="操作者"></el-table-column>
                <el-table-column prop="modifiedField" label="修改领域"></el-table-column>
                <el-table-column prop="oldValue" label="旧值"></el-table-column>
                <el-table-column prop="newValue" label="新值"></el-table-column>
                <el-table-column prop="remark" label="备注"></el-table-column>

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

                <el-button type="primary" @click="exportXlsx">导出历史数据</el-button>
			</div>
            
		</div>     
	</div>
</template>

<script setup lang="ts" name="basetable">
import { ref, reactive } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { Delete, Edit, Search, Plus, InfoFilled } from '@element-plus/icons-vue';
import { deleteCompany, getHistoryByCode, updateCompanyInfo, getCompanyByUnifiedSocialCredit, exportCompanies, getAllHistory, getHistoryByType, exportHistory} from '../api/index';
import { dataType } from 'element-plus/es/components/table-v2/src/common';
import router from '../router';
import * as XLSX from 'xlsx';

interface TableItem {
  recordId: string;
  companyCode: string;
  operationType: string;
  operationTime: string;
  operator: string;
  modifiedField: string;
  oldValue: string;
  newValue: string;
  remark: string;

}

const query = reactive({
	type: '',
	code: '',
	pageIndex: 1,
	pageSize: 10,
});
const tableData = ref<TableItem[]>([]);
const pageTotal = ref(0);

const getHistoryData = (page: number, size: number) => {
    console.log(page, size);
    getAllHistory(page, size)
    .then(response => {
      const data = response.data; 

      console.log('data:', data);
      tableData.value = data.content
      pageTotal.value = data.totalElements || 0; 

      for (const item of tableData.value) {
        const oldValue = item.oldValue;
        const newValue = item.newValue;
        if(oldValue == "" || newValue == ""){
          continue;
        }
        const oldCompany = deserializeCompany(oldValue);
        const newCompany = deserializeCompany(newValue);
        let oldValues = ""; // 存储旧值的字符串
        let newValues = ""; // 存储新值的字符串

        for (const key in oldCompany) {
          if (oldCompany[key] !== newCompany[key]) {
            if(key == "companyId"){
              continue;
            }
            if(key == "registeredLocation"){
              if( oldCompany[key] == "中国大陆" && newCompany[key] == "0"){
                continue;
              }
              if( oldCompany[key] == "中国香港" && newCompany[key] == "1"){
                continue;
              }
              if( oldCompany[key] == "境外" && newCompany[key] == "2"){
                continue;
              }
            }
            if(key == "registeredCapital") {
              const oldCapital = parseFloat(oldCompany[key]);
              const newCapital = parseFloat(newCompany[key]);
              if (isNaN(oldCapital) || isNaN(newCapital)) {
                continue;
              }
              if (oldCapital === newCapital) {
                continue;
              }
              oldValues += `'${key}': ${oldCapital}\n`;
              newValues += `'${key}': ${newCapital}\n`;
            } 
            else {
              oldValues += `'${key}': ${oldCompany[key]}\n`;
              newValues += `'${key}': ${newCompany[key]}\n`;
            }
            
          }
        }
        // 更新 item 对象的值
        item.oldValue = oldValues;
        item.newValue = newValues;

      }
    })
    .catch(error => {
      // 处理请求错误
      console.error('请求错误:', error);
    });
}
// 反序列化 oldCompany 和 newCompany
const deserializeCompany = (companyString: string) => {
  const propertyArray = companyString
    .replace("Company", "")
    .replace("{", "")
    .replace("}", "")
    .split(", ");

    const companyObject: { [key: string]: string } = {}; // 添加类型注解
  for (const property of propertyArray) {
    const [key, value] = property.split("=");
    companyObject[key.trim()] = value.replace(/'/g, "").trim();
  }
  return companyObject;
};


const getHistoryDataByCode = (page: number, size: number, code: string) => {
    getHistoryByCode(code, page, size)
    .then(response => {
        const data = response.data; 

        console.log('data:', data);
        tableData.value = data.content;
        pageTotal.value = data.totalElements || 0; 
        for (const item of tableData.value) {
        const oldValue = item.oldValue;
        const newValue = item.newValue;
        if(oldValue == "" || newValue == ""){
          continue;
        }
        const oldCompany = deserializeCompany(oldValue);
        const newCompany = deserializeCompany(newValue);
        let oldValues = ""; // 存储旧值的字符串
        let newValues = ""; // 存储新值的字符串

        for (const key in oldCompany) {
          if (oldCompany[key] !== newCompany[key]) {
            if(key == "companyId"){
              continue;
            }
            if(key == "registeredLocation"){
              if( oldCompany[key] == "中国大陆" && newCompany[key] == "0"){
                continue;
              }
              if( oldCompany[key] == "中国香港" && newCompany[key] == "1"){
                continue;
              }
              if( oldCompany[key] == "境外" && newCompany[key] == "2"){
                continue;
              }
            }
            if(key == "registeredCapital") {
              const oldCapital = parseFloat(oldCompany[key]);
              const newCapital = parseFloat(newCompany[key]);
              if (isNaN(oldCapital) || isNaN(newCapital)) {
                continue;
              }
              if (oldCapital === newCapital) {
                continue;
              }
              oldValues += `'${key}': ${oldCapital}\n`;
              newValues += `'${key}': ${newCapital}\n`;
            } 
            else {
              oldValues += `'${key}': ${oldCompany[key]}\n`;
              newValues += `'${key}': ${newCompany[key]}\n`;
            }
            
          }
        }
        // 更新 item 对象的值
        item.oldValue = oldValues;
        item.newValue = newValues;

      }
    })
    .catch(error => {
        // 处理请求错误
        console.error('请求错误:', error);
    });

}

const exportXlsx = () => {
    exportHistory()
    .then((response) => {
    console.log("exportHistory")
    // 创建一个 Blob 对象，用于保存二进制数据
    const blob = new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
    // 创建一个临时 URL，用于生成下载链接
    const url = URL.createObjectURL(blob);
    // 创建一个 <a> 元素并模拟点击以下载文件
        const link = document.createElement('a');
        link.href = url;
        link.download = 'history_record.xlsx';
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
const list = [["recordId",
                "companyCode",
                "operationType",
                "operationTime",
                "operator",
                "modifiedField",
                "oldValue",
                "newValue",
                "remark",
                ]];

const exportXlsxPage = () => {
  tableData.value.map((item: TableItem, i: number) => {
    const arr: any[] = [i + 1];
    arr.push(
      item.recordId,
      item.companyCode,
      item.operationType || "", // 如果字段可能为空，请进行适当的处理
      item.operationTime || "",
      item.operator,
      item.modifiedField,
      item.oldValue,
      item.newValue,
      item.remark,
    );
    list.push(arr);
  });
  let WorkSheet = XLSX.utils.aoa_to_sheet(list);
  let new_workbook = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(new_workbook, WorkSheet, "第一页");
  XLSX.writeFile(new_workbook, `数据.xlsx`);
};


getHistoryData(query.pageIndex - 1, query.pageSize);


// 查询操作
const handleSearch = () => {

	query.pageIndex = 1;
	let code = query.code;
  if(code == ""){
    getHistoryData(query.pageIndex - 1, query.pageSize);
    query.type = "";

  }else{
      getHistoryDataByCode((query.pageIndex - 1),  query.pageSize, code);
  }
    
  

};
const getHistoryDataByType = (page: number, size: number, type: string) => {
    console.log("type:", type)
    getHistoryByType(type, page, size)
    .then(response => {
        const data = response.data; 

        console.log('data:', data);
        tableData.value = data.content;
        pageTotal.value = data.totalElements || 0;
        for (const item of tableData.value) {
        const oldValue = item.oldValue;
        const newValue = item.newValue;
        if(oldValue == "" || newValue == ""){
          continue;
        }
        const oldCompany = deserializeCompany(oldValue);
        const newCompany = deserializeCompany(newValue);
        let oldValues = ""; // 存储旧值的字符串
        let newValues = ""; // 存储新值的字符串

        for (const key in oldCompany) {
          if (oldCompany[key] !== newCompany[key]) {
            if(key == "companyId"){
              continue;
            }
            if(key == "registeredLocation"){
              if( oldCompany[key] == "中国大陆" && newCompany[key] == "0"){
                continue;
              }
              if( oldCompany[key] == "中国香港" && newCompany[key] == "1"){
                continue;
              }
              if( oldCompany[key] == "境外" && newCompany[key] == "2"){
                continue;
              }
            }
            if(key == "registeredCapital") {
              const oldCapital = parseFloat(oldCompany[key]);
              const newCapital = parseFloat(newCompany[key]);
              if (isNaN(oldCapital) || isNaN(newCapital)) {
                continue;
              }
              if (oldCapital === newCapital) {
                continue;
              }
              oldValues += `'${key}': ${oldCapital}\n`;
              newValues += `'${key}': ${newCapital}\n`;
            } 
            else {
              oldValues += `'${key}': ${oldCompany[key]}\n`;
              newValues += `'${key}': ${newCompany[key]}\n`;
            }
            
          }
        }
        // 更新 item 对象的值
        item.oldValue = oldValues;
        item.newValue = newValues;

      }
         
    })
    .catch(error => {
        // 处理请求错误
        console.error('请求错误:', error);
    });

}

// 地点下拉框改变事件处理程序
const handleTypeChange = (value: string) => {
  query.type = value;
  console.log("query.type: ", query.type)
  query.pageIndex = 1;
  getHistoryDataByType((query.pageIndex - 1), query.pageSize, value);
};

// 分页导航
const handlePageChange = (val: number) => {
	query.pageIndex = val;
	getHistoryData((val - 1), query.pageSize);
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
