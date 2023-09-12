<template>
    <div class="container">
        <div class="form-box">
            <el-form ref="formRef" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="公司全称" prop="company_name">
                    <el-input v-model="form.company_name"></el-input>
                </el-form-item>

                <el-form-item label="公司简称" prop="company_abbreviation">
                    <el-input v-model="form.company_abbreviation"></el-input>
                </el-form-item>

                <el-form-item label="公司编码" prop="company_code">
                    <el-input v-model="form.company_code"></el-input>
                </el-form-item>

                <el-form-item label="公司类型" prop="company_type">
                    <el-input v-model="form.company_type"></el-input>
                </el-form-item>
                <el-form-item label="注册地" prop="registered_location">
                    <el-radio-group v-model="form.registered_location">
                        <el-radio label="中国大陆"></el-radio>
                        <el-radio label="中国香港"></el-radio>
                        <el-radio label="境外"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="统一社会信用编码" prop="unified_social_credit" class="small-gap">
                    <el-input v-model="form.unified_social_credit"></el-input>
                </el-form-item>

                <br>

                <el-form-item label="公司详细注册地址" prop="registered_address">
                    <el-input type="textarea" rows="5" v-model="form.registered_address" maxlength="200"></el-input>
                </el-form-item>

                <el-form-item label="注册电话" prop="registered_phone" >
                    <el-input v-model="form.registered_phone"></el-input>
                </el-form-item>
                <el-form-item label="公司邮箱" prop="company_email" >
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
                <el-form-item label="法定代表人姓名" prop="legal_representative_name" class="small-gap">
                    <el-input v-model="form.legal_representative_name"></el-input>
                </el-form-item>
                <br>
                <el-form-item label="法定代表人联系电话" prop="legal_representative_phone" class="big-gap">
                    <el-input v-model="form.legal_representative_phone"></el-input>
                </el-form-item>
                <el-form-item label="法定代表人身份证号"  prop="legal_representative_id" class="big-gap">
                    <el-input v-model="form.legal_representative_id"></el-input>
                </el-form-item>
                <el-form-item label="行业分类" prop="industry">
                    <el-input v-model="form.industry" maxlength="50"></el-input>
                </el-form-item>
                <el-form-item label="经营范围" prop="business_scope">
                    <el-input type="textarea" v-model="form.business_scope"></el-input>
                </el-form-item>
                
                <el-form-item label="是否为已认证客户"  prop="verified_customer" class="mid-gap">
                    <el-switch v-model="form.verified_customer"></el-switch>
                </el-form-item>

                <el-form-item label="是否为深交所会员" prop="szse_member" class="mid-gap">
                    <el-switch v-model="form.szse_member"></el-switch>
                </el-form-item>

                <template v-if="form.szse_member">
                    <el-form-item label="深交所会员编码" prop="szse_member_code" class="mid-gap" >
                        <el-input v-model="form.szse_member_code"></el-input>
                    </el-form-item>
                    <el-form-item label="深交所会员简称" prop="szse_member_abbreviation" class="mid-gap">
                        <el-input v-model="form.szse_member_abbreviation"></el-input>
                    </el-form-item>
                </template>

                <el-form-item label="客户状态" prop="customer_status">
                    <el-input v-model="form.customer_status"></el-input>
                </el-form-item>
                <el-form-item label="所在国家" prop="country">
                    <el-input v-model="form.country"></el-input>
                </el-form-item>

                <el-form-item label="所在省份" prop="province">
                    <el-input v-model="form.province"></el-input>
                </el-form-item>

                <el-form-item label="所在城市" prop="city">
                    <el-input v-model="form.city"></el-input>
                </el-form-item>
                <el-form-item label="营业执照编号" prop="business_license_number" class="mid-gap">
                    <el-input v-model="form.business_license_number"></el-input>
                </el-form-item>
                <el-form-item label="营业执照有效期" prop="business_license_expiry" class="mid-gap">
                    <el-date-picker v-model="form.business_license_expiry" type="date" placeholder="选择日期"></el-date-picker>
                </el-form-item>
                <el-form-item label="主要联系人姓名" prop="primary_contact_name" class="mid-gap">
                    <el-input v-model="form.primary_contact_name"></el-input>
                </el-form-item>
                <el-form-item label="主要联系人职务" prop="primary_contact_position" class="mid-gap">
                    <el-input v-model="form.primary_contact_position"></el-input>
                </el-form-item>
                <el-form-item label="主要联系人电话" prop="primary_contact_phone" class="mid-gap">
                    <el-input v-model="form.primary_contact_phone"></el-input>
                </el-form-item>
                <el-form-item label="主要联系人邮箱" prop="primary_contact_email" class="mid-gap">
                    <el-input v-model="form.primary_contact_email"></el-input>
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
import request from '../utils/request';

const validateUnifiedSocialCredit = (rule: any, value: any, callback: any) => {
  const regex = /^[0-9A-Z]{18}$/;
  if (value && !regex.test(value)) {
    callback(new Error('请输入有效的统一社会信用编码'));
  } else {
    callback();
  }
};

const validatePhoneNumber = (rule: any, value: any, callback: any) => {
 const regex = /^[0-9]+$/; // 仅限数字
  if (value && !regex.test(value)) {
    callback(new Error('请输入有效的电话号码'));
  } else {
    callback();
  }
};


const validateRegisteredCapital = (rule: any, value: any, callback: any) => {
  const regex = /^\d+(\.\d{1,2})?$/; // 数字格式，最多两位小数的正则表达式
  if (value && !regex.test(value)) {
    callback(new Error('请输入有效的注册资本，最多保留两位小数'));
  } else {
    callback();
  }
};

const validateIDCardNumber = (rule: any, value: any, callback: any) => {
  const regex = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; // 身份证号格式正则表达式
  if (value && !regex.test(value)) {
    callback(new Error('请输入有效的身份证号'));
  } else {
    callback();
  }
};



const rules: FormRules = {
    company_name: [{ required: true, message: '请输入公司全称', trigger: 'blur' }],
    company_code: [{ required: true, message: '请输入公司编码', trigger: 'blur' }],
    company_type: [{ required: true, message: '请输入公司类型', trigger: 'blur' }],
    registered_location: [{ required: true, message: '请选择公司注册地', trigger: 'change' }],
    unified_social_credit: [{ required: true, message: '请输入统一社会信用编码', trigger: 'blur' },
    { validator: validateUnifiedSocialCredit, trigger: 'blur' }],
    registered_address: [{ required: true, message: '请输入公司详细注册地址', trigger: 'blur' }],
    registered_phone: [{ required: true, message: '请输入注册电话', trigger: 'blur' },
    { validator: validatePhoneNumber, trigger: 'blur' }],

    company_email: [{ required: true, message: '请输入公司邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入有效的邮箱地址', trigger: ['blur', 'change'] },],

    establishment_date: [{ required: true, message: '请选择成立日期', trigger: 'change' }],

    registered_capital: [{ required: true, message: '请输入注册资本', trigger: 'blur' },
    { validator: validateRegisteredCapital, trigger: 'blur' }],

    legal_representative_name: [{ required: true, message: '请输入法定代表人姓名', trigger: 'blur' }],

    legal_representative_phone: [{ required: true, message: '法定代表人联系电话', trigger: 'blur' },
    { validator: validatePhoneNumber, trigger: 'blur' }],

    legal_representative_id: [
    { required: true, message: '请输入法定代表人身份证号', trigger: 'blur' },
    { validator: validateIDCardNumber, trigger: 'blur' }],

    industry: [{ required: true, message: '请输入行业分类', trigger: 'blur' }],

    business_scope: [{ required: true, message: '请输入经营范围', trigger: 'blur' }],
    verified_customer: [{ required: true, message: '请选择是否为已认证客户', trigger: 'change' }],
    szse_member: [{ required: true, message: '请选择是否为深交所会员', trigger: 'change' }],
    szse_member_code: [{ required: true, message: '请输入深交所会员编码', trigger: 'blur' }],
    szse_member_abbreviation: [{ required: true, message: '请输入深交所会员简称', trigger: 'blur' }],
    customer_status: [{ required: true, message: '请输入客户状态', trigger: 'blur' }],
    country: [{ required: true, message: '请输入所在国家', trigger: 'blur' }],
    province: [{ required: true, message: '请输入所在省份', trigger: 'blur' }],
    city: [{ required: true, message: '请输入所在城市', trigger: 'blur' }],
    business_license_number: [{ required: true, message: '请输入营业执照编号', trigger: 'blur' }],
    business_license_expiry: [{ required: true, message: '请选择营业执照有效期', trigger: 'change' }],
    primary_contact_name: [{ required: true, message: '请输入主要联系人姓名', trigger: 'blur' }],
    primary_contact_position: [{ required: true, message: '请输入主要联系人职务', trigger: 'blur' }],
    primary_contact_phone: [{ required: true, message: '请输入主要联系人电话', trigger: 'blur' },
    { validator: validatePhoneNumber, trigger: 'blur' }],

    primary_contact_email: [
        { required: true, message: '请输入主要联系人邮箱', trigger: 'blur' },
        { type: 'email', message: '请输入有效的邮箱地址', trigger: ['blur', 'change'] },
    ],




};
const formRef = ref<FormInstance>();
const form = reactive({
    company_name: '',
    company_code: '',
    company_abbreviation:'',
    company_type: '',
    registered_location: '',
    unified_social_credit: '',
    registered_address: '',
    registered_phone: '',
    company_email:'',
    establishment_date: '',
    registered_capital: '',
    legal_representative_name: '',
    legal_representative_phone:'',
    legal_representative_id:'', 
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
    primary_contact_email: '',
});
// 提交
const onSubmit = (formEl: FormInstance | undefined) => {
    // 表单校验
    if (!formEl) return;
    formEl.validate((valid) => {
        if (valid) {
            console.log(form);

            // 将注册地转换为数字
            let registeredLocationValue = "0";
            if (form.registered_location === "中国香港"){
                registeredLocationValue = "1";
            }else if (form.registered_location === "境外") {
                registeredLocationValue = "2";
            } 
            form.registered_location = registeredLocationValue;

            request.post('http://localhost:8080/companies/create', form)
                .then(response => {
                    console.log(response);
                    // 处理成功的情况
                    if (response.status == 201){
                        ElMessage.success("创建成功");
                    } else {
                        if (response.data.company_confilct) {
                            ElMessage.error("公司编码或统一社会信用编码已存在");
                        }else{
                            ElMessage.error("创建失败");
                        }
                    }
                })
                .catch(error => {
                    console.error(error);
                    // 处理错误的情况
            });
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
.small-gap {
  margin-bottom: 15px; 
}
.big-gap {
    margin-bottom: 70px; 
}
.mid-gap {
    margin-bottom: 40px;
}


</style>