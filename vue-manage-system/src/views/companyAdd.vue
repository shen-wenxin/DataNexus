<template>
    <div class="container">
        <div class="form-box">
            <el-form ref="formRef" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="公司全称" prop="companyName">
                    <el-input v-model="form.companyName"></el-input>
                </el-form-item>

                <el-form-item label="公司简称" prop="companyAbbreviation">
                    <el-input v-model="form.companyAbbreviation"></el-input>
                </el-form-item>

                <el-form-item label="公司编码" prop="companyCode">
                    <el-input v-model="form.companyCode"></el-input>
                </el-form-item>

                <el-form-item label="公司类型" prop="companyType">
                    <el-input v-model="form.companyType"></el-input>
                </el-form-item>
                <el-form-item label="注册地" prop="registeredLocation">
                    <el-radio-group v-model="form.registeredLocation">
                        <el-radio label="中国大陆"></el-radio>
                        <el-radio label="中国香港"></el-radio>
                        <el-radio label="境外"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="统一社会信用编码" prop="unifiedSocialCredit" class="small-gap">
                    <el-input v-model="form.unifiedSocialCredit"></el-input>
                </el-form-item>

                <br>

                <el-form-item label="公司详细注册地址" prop="registeredAddress">
                    <el-input type="textarea" rows="5" v-model="form.registeredAddress" maxlength="200"></el-input>
                </el-form-item>

                <el-form-item label="注册电话" prop="registeredPhone" >
                    <el-input v-model="form.registeredPhone"></el-input>
                </el-form-item>
                <el-form-item label="公司邮箱" prop="companyEmail" >
                    <el-input v-model="form.companyEmail"></el-input>
                </el-form-item>
                <el-form-item label="成立日期" prop="establishmentDate">
                    <el-date-picker
                        type="date"
                        placeholder="选择日期"
                        v-model="form.establishmentDate"
                        :rules="rules.establishmentDate"
                        style="width: 100%"
                    ></el-date-picker>
                </el-form-item>
                <el-form-item label="注册资本" prop="registeredCapital">
                    <el-input v-model="form.registeredCapital"></el-input>
                </el-form-item>
                <el-form-item label="法定代表人姓名" prop="legalRepresentativeName" class="small-gap">
                    <el-input v-model="form.legalRepresentativeName"></el-input>
                </el-form-item>
                <br>
                <el-form-item label="法定代表人联系电话" prop="legalRepresentativePhone" class="big-gap">
                    <el-input v-model="form.legalRepresentativePhone"></el-input>
                </el-form-item>
                <el-form-item label="法定代表人身份证号"  prop="legalRepresentativeId" class="big-gap">
                    <el-input v-model="form.legalRepresentativeId"></el-input>
                </el-form-item>
                <el-form-item label="行业分类" prop="industry">
                    <el-input v-model="form.industry" maxlength="50"></el-input>
                </el-form-item>
                <el-form-item label="经营范围" prop="businessScope">
                    <el-input type="textarea" v-model="form.businessScope"></el-input>
                </el-form-item>
                
                <el-form-item label="是否为已认证客户"  prop="verifiedCustomer" class="mid-gap">
                    <el-switch v-model="form.verifiedCustomer"></el-switch>
                </el-form-item>

                <el-form-item label="是否为深交所会员" prop="szseMember" class="mid-gap">
                    <el-switch v-model="form.szseMember"></el-switch>
                </el-form-item>

                <template v-if="form.szseMember">
                    <el-form-item label="深交所会员编码" prop="szseMemberCode" class="mid-gap" >
                        <el-input v-model="form.szseMemberCode"></el-input>
                    </el-form-item>
                    <el-form-item label="深交所会员简称" prop="szseMemberAbbreviation" class="mid-gap">
                        <el-input v-model="form.szseMemberAbbreviation"></el-input>
                    </el-form-item>
                </template>

                <el-form-item label="客户状态" prop="customerStatus">
                    <el-input v-model="form.customerStatus"></el-input>
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
                <el-form-item label="营业执照编号" prop="businessLicenseNumber" class="mid-gap">
                    <el-input v-model="form.businessLicenseNumber"></el-input>
                </el-form-item>
                <el-form-item label="营业执照有效期" prop="businessLicenseExpiry" class="mid-gap">
                    <el-date-picker v-model="form.businessLicenseExpiry" type="date" placeholder="选择日期"></el-date-picker>
                </el-form-item>
                <el-form-item label="主要联系人姓名" prop="primaryContactName" class="mid-gap">
                    <el-input v-model="form.primaryContactName"></el-input>
                </el-form-item>
                <el-form-item label="主要联系人职务" prop="primaryContactPosition" class="mid-gap">
                    <el-input v-model="form.primaryContactPosition"></el-input>
                </el-form-item>
                <el-form-item label="主要联系人电话" prop="primaryContactPhone" class="mid-gap">
                    <el-input v-model="form.primaryContactPhone"></el-input>
                </el-form-item>
                <el-form-item label="主要联系人邮箱" prop="primaryContactEmail" class="mid-gap">
                    <el-input v-model="form.primaryContactEmail"></el-input>
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
import router from '../router';

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
    companyName: [{ required: true, message: '请输入公司全称', trigger: 'blur' }],
    companyCode: [{ required: true, message: '请输入公司编码', trigger: 'blur' }],
    companyType: [{ required: true, message: '请输入公司类型', trigger: 'blur' }],
    registeredLocation: [{ required: true, message: '请选择公司注册地', trigger: 'change' }],
    unifiedSocialCredit: [{ required: true, message: '请输入统一社会信用编码', trigger: 'blur' },
    { validator: validateUnifiedSocialCredit, trigger: 'blur' }],
    registeredAddress: [{ required: true, message: '请输入公司详细注册地址', trigger: 'blur' }],
    registeredPhone: [{ required: true, message: '请输入注册电话', trigger: 'blur' },
    { validator: validatePhoneNumber, trigger: 'blur' }],

    companyEmail: [{ required: true, message: '请输入公司邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入有效的邮箱地址', trigger: ['blur', 'change'] },],

    establishmentDate: [{ required: true, message: '请选择成立日期', trigger: 'change' }],

    registeredCapital: [{ required: true, message: '请输入注册资本', trigger: 'blur' },
    { validator: validateRegisteredCapital, trigger: 'blur' }],

    legalRepresentativeName: [{ required: true, message: '请输入法定代表人姓名', trigger: 'blur' }],

    legalRepresentativePhone: [{ required: true, message: '法定代表人联系电话', trigger: 'blur' },
    { validator: validatePhoneNumber, trigger: 'blur' }],

    legalRepresentativeId: [
    { required: true, message: '请输入法定代表人身份证号', trigger: 'blur' },
    { validator: validateIDCardNumber, trigger: 'blur' }],

    industry: [{ required: true, message: '请输入行业分类', trigger: 'blur' }],

    businessScope: [{ required: true, message: '请输入经营范围', trigger: 'blur' }],
    verifiedCustomer: [{ required: true, message: '请选择是否为已认证客户', trigger: 'change' }],
    szseMember: [{ required: true, message: '请选择是否为深交所会员', trigger: 'change' }],
    szseMemberCode: [{ required: true, message: '请输入深交所会员编码', trigger: 'blur' }],
    szseMemberAbbreviation: [{ required: true, message: '请输入深交所会员简称', trigger: 'blur' }],
    customerStatus: [{ required: true, message: '请输入客户状态', trigger: 'blur' }],
    country: [{ required: true, message: '请输入所在国家', trigger: 'blur' }],
    province: [{ required: true, message: '请输入所在省份', trigger: 'blur' }],
    city: [{ required: true, message: '请输入所在城市', trigger: 'blur' }],
    businessLicenseNumber: [{ required: true, message: '请输入营业执照编号', trigger: 'blur' }],
    businessLicenseExpiry: [{ required: true, message: '请选择营业执照有效期', trigger: 'change' }],
    primaryContactName: [{ required: true, message: '请输入主要联系人姓名', trigger: 'blur' }],
    primaryContactPosition: [{ required: true, message: '请输入主要联系人职务', trigger: 'blur' }],
    primaryContactPhone: [{ required: true, message: '请输入主要联系人电话', trigger: 'blur' },
    { validator: validatePhoneNumber, trigger: 'blur' }],

    primaryContactEmail: [
        { required: true, message: '请输入主要联系人邮箱', trigger: 'blur' },
        { type: 'email', message: '请输入有效的邮箱地址', trigger: ['blur', 'change'] },
    ],




};
const formRef = ref<FormInstance>();
const form = reactive({
    companyName: '',
    companyCode: '',
    companyAbbreviation:'',
    companyType: '',
    registeredLocation: '',
    unifiedSocialCredit: '',
    registeredAddress: '',
    registeredPhone: '',
    companyEmail:'',
    establishmentDate:'',
    registeredCapital: '',
    legalRepresentativeName: '',
    legalRepresentativePhone:'',
    legalRepresentativeId:'', 
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
    primaryContactEmail: '',
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
            if (form.registeredLocation === "中国香港"){
                registeredLocationValue = "1";
            }else if (form.registeredLocation === "境外") {
                registeredLocationValue = "2";
            } 
            form.registeredLocation = registeredLocationValue;
            console.log("add company")

            console.log("time check1", form.establishmentDate);
            console.log("time check2", form.businessLicenseExpiry);

            

            request.post('http://localhost:8080/companies/create', form)
                .then(response => {
                    console.log(response);
                    // 处理成功的情况
                    if (response.status == 201){
                        ElMessage.success("创建成功");
                        router.push('/companies');
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