<template>
    <div class="container">
        <div class="form-box">
            <el-form ref="formRef" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="公司全称" prop="name">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>

                <el-form-item label="公司简称" prop="abbreviation">
                    <el-input v-model="form.abbreviation"></el-input>
                </el-form-item>

                <el-form-item label="公司编码" prop="code">
                    <el-input v-model="form.code"></el-input>
                </el-form-item>

                <el-form-item label="公司类型" prop="companytype">
                    <el-input v-model="form.companytype"></el-input>
                </el-form-item>
                <el-form-item label="注册地" prop="registered_location">
                    <el-radio-group v-model="form.registered_location">
                        <el-radio label="中国大陆"></el-radio>
                        <el-radio label="中国香港"></el-radio>
                        <el-radio label="境外"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="统一社会信用编码" prop="unified_social_credit" class="credit-code-item">
                    <el-input v-model="form.unified_social_credit"></el-input>
                </el-form-item>

                <br>

                <el-form-item label="公司详细注册地址" prop="registered_address">
                    <el-input type="textarea" rows="5" v-model="form.registered_address" maxlength="200"></el-input>
                </el-form-item>

                <el-form-item label="注册电话" prop="registered_phone" >
                    <el-input v-model="form.registered_phone"></el-input>
                </el-form-item>
                <el-form-item label="注册邮箱" prop="company_email" >
                    <el-input v-model="form.company_email"></el-input>
                </el-form-item>
                <el-form-item label="成立日期" prop="establishment_date">
                    <el-date-picker
                        type="date"
                        placeholder="选择日期"
                        v-model="form.establishment_date"
                        :rules="rules.establishment_date"
                        style="width: 100%"
                    ></el-date-picker>
                </el-form-item>
                <el-form-item label="注册资本" prop="registered_capital">
                    <el-input v-model="form.registered_capital"></el-input>
                </el-form-item>
                <el-form-item label="法定代表人姓名" prop="legal_representative_name" class="legal-phone-item">
                    <el-input v-model="form.legal_representative_name"></el-input>
                </el-form-item>
                <br>
                <el-form-item label="法定代表人联系电话" prop="legal_representative_phone" class="legal-phone-item">
                    <el-input v-model="form.legal_representative_phone"></el-input>
                </el-form-item>
                <el-form-item label="法定代表人身份证号"  prop="legal_representative_id" class="legal-phone-item">
                    <el-input v-model="form.legal_representative_id"></el-input>
                </el-form-item>
                <el-form-item label="行业分类" prop="industry">
                    <el-input v-model="form.industry"></el-input>
                </el-form-item>
                <el-form-item label="经营范围" prop="business_scope">
                    <el-input type="textarea" v-model="form.business_scope"></el-input>
                </el-form-item>
                <el-form-item label="是否为已认证客户" prop="is_verified_customer" class="legal-phone-item">
                    <el-radio-group v-model="form.is_verified_customer">
                        <el-radio label="true">是</el-radio>
                        <el-radio label="false">否</el-radio>
                    </el-radio-group>
                </el-form-item>




                <el-form-item>
                    <el-button type="primary" @click="onSubmit(formRef)">表单提交</el-button>
                    <el-button @click="onReset(formRef)">重置表单</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script setup lang="ts" name="baseform">
import { reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';
import type { FormInstance, FormRules } from 'element-plus';


const rules: FormRules = {
    name: [{ required: true, message: '请输入公司全称', trigger: 'blur' }],
    code: [{ required: true, message: '请输入公司编码', trigger: 'blur' }],
    companytype: [{ required: true, message: '请输入公司类型', trigger: 'blur' }],
    registered_location: [{ required: true, message: '请选择公司注册地', trigger: 'change' }],
    unified_social_credit: [{ required: true, message: '请输入统一社会信用编码', trigger: 'blur' }],
    registered_address: [{ required: true, message: '请输入公司详细注册地址', trigger: 'blur' }],
    registered_phone: [{ required: true, message: '请输入注册电话', trigger: 'blur' }],
    company_email: [{ required: true, message: '请输入公司邮箱', trigger: 'blur' }],
    establishment_date: [{ required: true, message: '请选择成立日期', trigger: 'change' }],
    registered_capital: [{ required: true, message: '请输入注册资本', trigger: 'blur' }],
    legal_representative_name: [{ required: true, message: '请输入法定代表人姓名', trigger: 'blur' }],
    legal_representative_phone: [{ required: true, message: '法定代表人联系电话', trigger: 'blur' }],
    legal_representative_id: [
    { required: true, message: '请输入法定代表人身份证号', trigger: 'blur' }],

    industry: [{ required: true, message: '请输入行业分类', trigger: 'blur' }],

    business_scope: [{ required: true, message: '请输入经营范围', trigger: 'blur' }],
    is_verified_customer: [{ required: true, message: '请选择是否为已认证客户', trigger: 'change' }],



};
const formRef = ref<FormInstance>();
const form = reactive({
    name: '',
    code: '',
    abbreviation:'',
    companytype: '',
    registered_location: '',
    unified_social_credit: '',// TODO: 确认格式是否正确
    registered_address: '',
    registered_phone: '',// TODO: 验证格式是否正确
    company_email:'',// TODO: 验证格式是否正确
    establishment_date: '',
    registered_capital: '',// TODO 验证格式是否正确
    legal_representative_name: '',
    legal_representative_phone:'',// TODO 验证格式是否正确
    legal_representative_id:'', // TODO 验证身份证号
    industry: '',
    business_scope: '',
    is_verified_customer: false,



});
// 提交
const onSubmit = (formEl: FormInstance | undefined) => {
    // 表单校验
    if (!formEl) return;
    formEl.validate((valid) => {
        if (valid) {
            console.log(form);
            ElMessage.success('提交成功！');
        } else {
            return false;
        }
    });
};
// 重置
const onReset = (formEl: FormInstance | undefined) => {
    if (!formEl) return;
    formEl.resetFields();
};
</script>
<style>
.credit-code-item {
  margin-bottom: 15px; 
}
.legal-phone-item{
    margin-bottom: 70px; 
}


</style>