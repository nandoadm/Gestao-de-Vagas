//package com.br.gestao_vagas.modules.company.controllers;
//
//import com.br.gestao_vagas.company.dto.CreateJobDTO;
//import com.br.gestao_vagas.company.entity.CompanyEntity;
//import com.br.gestao_vagas.company.repository.CompanyRepository;
//import com.br.gestao_vagas.utils.TestUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
//
//
//import java.util.UUID;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//public class CreateJobControllerTest {
//
//    private MockMvc mvc;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    private CompanyRepository companyRepository;
//
//    @Before
//    public void setup(){
//        mvc = MockMvcBuilders.webAppContextSetup(context)
//                .apply(org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity())
//                .build();
//    }
//
//    @Test
//    public void should_be_able_to_create_a_new_job() throws Exception{
//
//        var company = CompanyEntity.builder()
//                .description("COMPANY_DESCRIPTION")
//                .email("email@company.com")
//                .password("1234567890")
//                .username("COMPANY_USERNAME")
//                .name("COMPANY_NAME").build();
//
//        company = companyRepository.saveAndFlush(company);
//
//        var createdJobDTO = CreateJobDTO.builder()
//                .benefits("BENEFITS_TEST")
//                .description("DESCRIPTION_TEST")
//                .level("LEVEL_TEST")
//                .build();
//
//        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(TestUtils.objectToJson(createdJobDTO)))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        System.out.println(result);
//
//    }
//
//
//
//    @Test
//    public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception{
//        var createdJobDTO = CreateJobDTO.builder()
//                .benefits("BENEFITS_TEST")
//                .description("DESCRIPTION_TEST")
//                .level("LEVEL_TEST")
//                .build();
//
//        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(TestUtils.objectToJson(createdJobDTO))
//                        .header("Authorization", TestUtils.generateToken(UUID.randomUUID())))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//    }
//}