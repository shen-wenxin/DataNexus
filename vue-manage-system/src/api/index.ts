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