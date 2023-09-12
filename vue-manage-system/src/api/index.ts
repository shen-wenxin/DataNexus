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