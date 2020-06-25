package com.numero.uno.wedify.wedify.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CompanyIntegrationTests /*extends BaseIntegrationTest*/ {

//	@Mock
//	private CompanyRepository companyRepository;
//
//	@Mock
//	private PasswordEncoder passwordEncoder;
//
//	@Autowired
//	@InjectMocks
//	private CompanyServiceImpl companyService;
//
//	@Override
//	protected Object getControllerUnderTest() {
//		return new CompanyController(companyService);
//	}
//
//	@Test
//	public void testInsertCompany_isCreated() throws Exception {
//		Company company = generateCompanies().get(0);
//
//		Mockito.when(companyRepository.save(Mockito.any())).thenReturn(company);
//
//		mockMvc.perform(post("/companies")
//				.content(objectMapper.writeValueAsString(company))
//				.contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andDo(print())
//				.andExpect(status().isCreated())
//				.andDo(mvcResult -> {
//					String jsonResponse = mvcResult.getResponse().getContentAsString();
//					JSONAssert.assertEquals(objectMapper.writeValueAsString(company), jsonResponse, false);
//				});
//	}
//
//	@Test
//	public void testInsertEmptyCompany_isBadRequest() throws Exception {
//
//		Company company = new Company();
//		String expectedTargetErrorMessage = "This field is required.";
//
//		ErrorResponse errorResponse = new ErrorResponse(
//				ApiErrorCode.INVALID_ARGUMENTS,
//				"Invalid arguments have been supplied.",
//				new TargetError("name", expectedTargetErrorMessage),
//				new TargetError("phoneNumber", expectedTargetErrorMessage),
//				new TargetError("password", expectedTargetErrorMessage),
//				new TargetError("email", expectedTargetErrorMessage),
//				new TargetError("kvk", expectedTargetErrorMessage),
//				new TargetError("description", expectedTargetErrorMessage),
//				new TargetError("postalCode", expectedTargetErrorMessage),
//				new TargetError("street", expectedTargetErrorMessage),
//				new TargetError("city", expectedTargetErrorMessage),
//				new TargetError("houseNumber", expectedTargetErrorMessage)
//		);
//
//		String companyJson = objectMapper.writeValueAsString(company);
//		String errorJson = objectMapper.writeValueAsString(errorResponse);
//
//		mockMvc.perform(post("/companies")
//				.content(companyJson)
//				.contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andDo(print())
//				.andExpect(status().isBadRequest())
//				.andDo(mvcResult -> {
//					String jsonResponse = mvcResult.getResponse().getContentAsString();
//					JSONAssert.assertEquals(errorJson, jsonResponse, false);
//				});
//	}
//
//	@Test
//	public void testGetCompany_isSuccess() throws Exception {
//		Company expectedCompany = generateCompanies().get(0);
//		Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(expectedCompany));
//
//		String expectedResponse = objectMapper.writeValueAsString(expectedCompany);
//
//		mockMvc.perform(get("/companies/1"))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andDo(mvcResult -> {
//					String jsonResponse = mvcResult.getResponse().getContentAsString();
//					JSONAssert.assertEquals(expectedResponse, jsonResponse, false);
//				});
//	}
//
//	@Test
//	public void testGetAllCompanies_isSuccess() throws Exception {
//		List<Company> expectedCompanies = generateCompanies();
//		Mockito.when(companyRepository.findAll()).thenReturn(expectedCompanies);
//
//		String expectedResponse = objectMapper.writeValueAsString(expectedCompanies);
//
//		mockMvc.perform(get("/companies"))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andDo(mvcResult -> {
//					String jsonResponse = mvcResult.getResponse().getContentAsString();
//					JSONAssert.assertEquals(expectedResponse, jsonResponse, false);
//				});
//	}
//
//	/**
//	 * @return List<Company> A list of companies for testing purposes.
//	 */
//	private List<Company> generateCompanies() {
//
//		return new ArrayList<Company>() {
//			{
//				add(new Company(1L, "Zaalverhuur Amsterdam", "0201948576", "pass123",
//						"zams@gmail.com", "kvk123", "Goede zaalverhuur", "1183TP",
//						"Amstelveense Weg", "Amsterdam", "66A", "www.zams.com"));
//			}
//		};
//	}
}
