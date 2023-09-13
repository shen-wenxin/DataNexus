import request from '../utils/request';

export const fetchData = () => {
    return request({
        url: './table.json',
        method: 'get'
    });
};

export const getAllCompanies = (page: number, size: number) => {
    const url = 'http://localhost:8080/companies'
    return request({
        url: url,
        method: 'GET',
        params: {
            page: page,
            size: size
          }
    });
};

export const getCompaniesByRegisteredLocation = (location:string, page: number, size: number) => {
    const url = 'http://localhost:8080/companies/search/registered_location'
    return request({
        url: url,
        method : 'GET',
        params : {
            registeredLocation: location,
            page: page,
            size: size
        }
    })

};

export const updateCompanyInfo = (form: any) =>{
    const url = 'http://localhost:8080/companies/update';

    return request({
        url: url,
        method : 'POST',
        data: form
    })
}

export const deleteCompany = (companyCode: string) => {
    const url = `http://localhost:8080/companies/${companyCode}`;
    console.log("get in deleteCompany");
    console.log(companyCode);

    return request({
        url: url,
        method: 'DELETE'
    });
};

export const getCompanyByUnifiedSocialCredit = (unifiedSocialCredit: string) => {
    const url = `http://localhost:8080/companies/${unifiedSocialCredit}`;
    return request({
      url: url,
      method: 'GET'
    });
};

export const getCompanyCount =() => {
    const url = `http://localhost:8080/companies/count`;
    return request({
        url: url,
        method: 'GET'
      });

}

export const exportCompanies = () => {
    console.log("Get in exportCompanies")
    const url = 'http://localhost:8080/companies/export';
    return request({
        url: url,
        method: 'GET',
        responseType: 'arraybuffer'  // 设置响应类型为二进制数组
    })
};

export const exportHistory = () => {
    console.log("Get in exportHistory")
    const url = 'http://localhost:8080/history-records/export';
    return request({
        url: url,
        method: 'GET',
        responseType: 'arraybuffer'  // 设置响应类型为二进制数组
    })
};


export const getAllHistory = (page: number, size: number) => {
    const url = 'http://localhost:8080/history-records/paged'
    return request({
        url: url,
        method: 'GET',
        params: {
            page: page,
            size: size
          }
    });
};

export const getHistoryByCode = (code:string, page: number, size: number) => {
    const url = 'http://localhost:8080/companies/history-records/paged/code'
    return request({
        url: url,
        method : 'GET',
        params : {
            page: page,
            size: size,
            code: code,
        }
    })

};

export const getHistoryByType = (type:string, page: number, size: number) => {
    const url = 'http://localhost:8080/companies/history-records/paged/type'
    return request({
        url: url,
        method : 'GET',
        params : {
            page: page,
            size: size,
            type: type,
        }
    })

};