package com.szsc.customermanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szsc.customermanagement.controller.CompanyController;
import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import com.szsc.customermanagement.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerManagementApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

	@Test
	void createCompany_ReturnsCreatedCompany() throws Exception {
		CompanyDTO company = new CompanyDTO();
		company.setCompanyCode("ABC");
		company.setCompanyName("Company 1");
		company.setCompanyAbbreviation("C1");
		company.setCompanyType("Type A");
	
		// 模拟 void 方法调用
		doNothing().when(companyService).createCompany(company);
	
		mockMvc.perform(MockMvcRequestBuilders.post("/companies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(company)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	
		verify(companyService, times(1)).createCompany(company);
	}

    @Test
    void getCompanyById_ReturnsCompany() throws Exception {
        int companyId = 1;
        CompanyDTO company = new CompanyDTO();
        company.setCompanyCode("ABC");
        company.setCompanyName("Company 1");
        company.setCompanyAbbreviation("C1");
        company.setCompanyType("Type A");

        when(companyService.getCompanyById(companyId)).thenReturn(company);

        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{companyId}", companyId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyCode").value(company.getCompanyCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName").value(company.getCompanyName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyAbbreviation").value(company.getCompanyAbbreviation()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyType").value(company.getCompanyType()));

        verify(companyService, times(1)).getCompanyById(companyId);
    }

    // Helper method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}