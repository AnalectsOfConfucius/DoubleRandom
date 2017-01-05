package com.yyh.web.rest;

import com.yyh.DrApp;

import com.yyh.domain.DoubleRandom;
import com.yyh.repository.DoubleRandomRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DoubleRandomResource REST controller.
 *
 * @see DoubleRandomResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DrApp.class)
public class DoubleRandomResourceIntTest {

    private static final String DEFAULT_DOUBLE_RANDOM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOUBLE_RANDOM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DOUBLE_RANDOM_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DOUBLE_RANDOM_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DOUBLE_RANDOM_NOTARY = "AAAAAAAAAA";
    private static final String UPDATED_DOUBLE_RANDOM_NOTARY = "BBBBBBBBBB";

    private static final String DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_1 = "AAAAAAAAAA";
    private static final String UPDATED_DOUBLE_RANDOM_COMPANY_CONDITION_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_2 = "AAAAAAAAAA";
    private static final String UPDATED_DOUBLE_RANDOM_COMPANY_CONDITION_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_3 = "AAAAAAAAAA";
    private static final String UPDATED_DOUBLE_RANDOM_COMPANY_CONDITION_3 = "BBBBBBBBBB";

    private static final String DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_4 = "AAAAAAAAAA";
    private static final String UPDATED_DOUBLE_RANDOM_COMPANY_CONDITION_4 = "BBBBBBBBBB";

    private static final String DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_1 = "AAAAAAAAAA";
    private static final String UPDATED_DOUBLE_RANDOM_MANAGER_CONDITION_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_2 = "AAAAAAAAAA";
    private static final String UPDATED_DOUBLE_RANDOM_MANAGER_CONDITION_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_3 = "AAAAAAAAAA";
    private static final String UPDATED_DOUBLE_RANDOM_MANAGER_CONDITION_3 = "BBBBBBBBBB";

    private static final String DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_4 = "AAAAAAAAAA";
    private static final String UPDATED_DOUBLE_RANDOM_MANAGER_CONDITION_4 = "BBBBBBBBBB";

    @Inject
    private DoubleRandomRepository doubleRandomRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restDoubleRandomMockMvc;

    private DoubleRandom doubleRandom;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DoubleRandomResource doubleRandomResource = new DoubleRandomResource();
        ReflectionTestUtils.setField(doubleRandomResource, "doubleRandomRepository", doubleRandomRepository);
        this.restDoubleRandomMockMvc = MockMvcBuilders.standaloneSetup(doubleRandomResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DoubleRandom createEntity(EntityManager em) {
        DoubleRandom doubleRandom = new DoubleRandom()
                .doubleRandomName(DEFAULT_DOUBLE_RANDOM_NAME)
                .doubleRandomDate(DEFAULT_DOUBLE_RANDOM_DATE)
                .doubleRandomNotary(DEFAULT_DOUBLE_RANDOM_NOTARY)
                .doubleRandomCompanyCondition1(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_1)
                .doubleRandomCompanyCondition2(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_2)
                .doubleRandomCompanyCondition3(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_3)
                .doubleRandomCompanyCondition4(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_4)
                .doubleRandomManagerCondition1(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_1)
                .doubleRandomManagerCondition2(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_2)
                .doubleRandomManagerCondition3(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_3)
                .doubleRandomManagerCondition4(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_4);
        return doubleRandom;
    }

    @Before
    public void initTest() {
        doubleRandom = createEntity(em);
    }

    @Test
    @Transactional
    public void createDoubleRandom() throws Exception {
        int databaseSizeBeforeCreate = doubleRandomRepository.findAll().size();

        // Create the DoubleRandom

        restDoubleRandomMockMvc.perform(post("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doubleRandom)))
            .andExpect(status().isCreated());

        // Validate the DoubleRandom in the database
        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeCreate + 1);
        DoubleRandom testDoubleRandom = doubleRandomList.get(doubleRandomList.size() - 1);
        assertThat(testDoubleRandom.getDoubleRandomName()).isEqualTo(DEFAULT_DOUBLE_RANDOM_NAME);
        assertThat(testDoubleRandom.getDoubleRandomDate()).isEqualTo(DEFAULT_DOUBLE_RANDOM_DATE);
        assertThat(testDoubleRandom.getDoubleRandomNotary()).isEqualTo(DEFAULT_DOUBLE_RANDOM_NOTARY);
        assertThat(testDoubleRandom.getDoubleRandomCompanyCondition1()).isEqualTo(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_1);
        assertThat(testDoubleRandom.getDoubleRandomCompanyCondition2()).isEqualTo(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_2);
        assertThat(testDoubleRandom.getDoubleRandomCompanyCondition3()).isEqualTo(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_3);
        assertThat(testDoubleRandom.getDoubleRandomCompanyCondition4()).isEqualTo(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_4);
        assertThat(testDoubleRandom.getDoubleRandomManagerCondition1()).isEqualTo(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_1);
        assertThat(testDoubleRandom.getDoubleRandomManagerCondition2()).isEqualTo(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_2);
        assertThat(testDoubleRandom.getDoubleRandomManagerCondition3()).isEqualTo(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_3);
        assertThat(testDoubleRandom.getDoubleRandomManagerCondition4()).isEqualTo(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_4);
    }

    @Test
    @Transactional
    public void createDoubleRandomWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = doubleRandomRepository.findAll().size();

        // Create the DoubleRandom with an existing ID
        DoubleRandom existingDoubleRandom = new DoubleRandom();
        existingDoubleRandom.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDoubleRandomMockMvc.perform(post("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDoubleRandom)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDoubleRandomNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = doubleRandomRepository.findAll().size();
        // set the field null
        doubleRandom.setDoubleRandomName(null);

        // Create the DoubleRandom, which fails.

        restDoubleRandomMockMvc.perform(post("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doubleRandom)))
            .andExpect(status().isBadRequest());

        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRandomDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = doubleRandomRepository.findAll().size();
        // set the field null
        doubleRandom.setDoubleRandomDate(null);

        // Create the DoubleRandom, which fails.

        restDoubleRandomMockMvc.perform(post("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doubleRandom)))
            .andExpect(status().isBadRequest());

        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRandomNotaryIsRequired() throws Exception {
        int databaseSizeBeforeTest = doubleRandomRepository.findAll().size();
        // set the field null
        doubleRandom.setDoubleRandomNotary(null);

        // Create the DoubleRandom, which fails.

        restDoubleRandomMockMvc.perform(post("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doubleRandom)))
            .andExpect(status().isBadRequest());

        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRandomCompanyCondition1IsRequired() throws Exception {
        int databaseSizeBeforeTest = doubleRandomRepository.findAll().size();
        // set the field null
        doubleRandom.setDoubleRandomCompanyCondition1(null);

        // Create the DoubleRandom, which fails.

        restDoubleRandomMockMvc.perform(post("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doubleRandom)))
            .andExpect(status().isBadRequest());

        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRandomCompanyCondition2IsRequired() throws Exception {
        int databaseSizeBeforeTest = doubleRandomRepository.findAll().size();
        // set the field null
        doubleRandom.setDoubleRandomCompanyCondition2(null);

        // Create the DoubleRandom, which fails.

        restDoubleRandomMockMvc.perform(post("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doubleRandom)))
            .andExpect(status().isBadRequest());

        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRandomCompanyCondition3IsRequired() throws Exception {
        int databaseSizeBeforeTest = doubleRandomRepository.findAll().size();
        // set the field null
        doubleRandom.setDoubleRandomCompanyCondition3(null);

        // Create the DoubleRandom, which fails.

        restDoubleRandomMockMvc.perform(post("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doubleRandom)))
            .andExpect(status().isBadRequest());

        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRandomCompanyCondition4IsRequired() throws Exception {
        int databaseSizeBeforeTest = doubleRandomRepository.findAll().size();
        // set the field null
        doubleRandom.setDoubleRandomCompanyCondition4(null);

        // Create the DoubleRandom, which fails.

        restDoubleRandomMockMvc.perform(post("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doubleRandom)))
            .andExpect(status().isBadRequest());

        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRandomManagerCondition1IsRequired() throws Exception {
        int databaseSizeBeforeTest = doubleRandomRepository.findAll().size();
        // set the field null
        doubleRandom.setDoubleRandomManagerCondition1(null);

        // Create the DoubleRandom, which fails.

        restDoubleRandomMockMvc.perform(post("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doubleRandom)))
            .andExpect(status().isBadRequest());

        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRandomManagerCondition2IsRequired() throws Exception {
        int databaseSizeBeforeTest = doubleRandomRepository.findAll().size();
        // set the field null
        doubleRandom.setDoubleRandomManagerCondition2(null);

        // Create the DoubleRandom, which fails.

        restDoubleRandomMockMvc.perform(post("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doubleRandom)))
            .andExpect(status().isBadRequest());

        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRandomManagerCondition3IsRequired() throws Exception {
        int databaseSizeBeforeTest = doubleRandomRepository.findAll().size();
        // set the field null
        doubleRandom.setDoubleRandomManagerCondition3(null);

        // Create the DoubleRandom, which fails.

        restDoubleRandomMockMvc.perform(post("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doubleRandom)))
            .andExpect(status().isBadRequest());

        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDoubleRandomManagerCondition4IsRequired() throws Exception {
        int databaseSizeBeforeTest = doubleRandomRepository.findAll().size();
        // set the field null
        doubleRandom.setDoubleRandomManagerCondition4(null);

        // Create the DoubleRandom, which fails.

        restDoubleRandomMockMvc.perform(post("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doubleRandom)))
            .andExpect(status().isBadRequest());

        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDoubleRandoms() throws Exception {
        // Initialize the database
        doubleRandomRepository.saveAndFlush(doubleRandom);

        // Get all the doubleRandomList
        restDoubleRandomMockMvc.perform(get("/api/double-randoms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(doubleRandom.getId().intValue())))
            .andExpect(jsonPath("$.[*].doubleRandomName").value(hasItem(DEFAULT_DOUBLE_RANDOM_NAME.toString())))
            .andExpect(jsonPath("$.[*].doubleRandomDate").value(hasItem(DEFAULT_DOUBLE_RANDOM_DATE.toString())))
            .andExpect(jsonPath("$.[*].doubleRandomNotary").value(hasItem(DEFAULT_DOUBLE_RANDOM_NOTARY.toString())))
            .andExpect(jsonPath("$.[*].doubleRandomCompanyCondition1").value(hasItem(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_1.toString())))
            .andExpect(jsonPath("$.[*].doubleRandomCompanyCondition2").value(hasItem(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_2.toString())))
            .andExpect(jsonPath("$.[*].doubleRandomCompanyCondition3").value(hasItem(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_3.toString())))
            .andExpect(jsonPath("$.[*].doubleRandomCompanyCondition4").value(hasItem(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_4.toString())))
            .andExpect(jsonPath("$.[*].doubleRandomManagerCondition1").value(hasItem(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_1.toString())))
            .andExpect(jsonPath("$.[*].doubleRandomManagerCondition2").value(hasItem(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_2.toString())))
            .andExpect(jsonPath("$.[*].doubleRandomManagerCondition3").value(hasItem(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_3.toString())))
            .andExpect(jsonPath("$.[*].doubleRandomManagerCondition4").value(hasItem(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_4.toString())));
    }

    @Test
    @Transactional
    public void getDoubleRandom() throws Exception {
        // Initialize the database
        doubleRandomRepository.saveAndFlush(doubleRandom);

        // Get the doubleRandom
        restDoubleRandomMockMvc.perform(get("/api/double-randoms/{id}", doubleRandom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(doubleRandom.getId().intValue()))
            .andExpect(jsonPath("$.doubleRandomName").value(DEFAULT_DOUBLE_RANDOM_NAME.toString()))
            .andExpect(jsonPath("$.doubleRandomDate").value(DEFAULT_DOUBLE_RANDOM_DATE.toString()))
            .andExpect(jsonPath("$.doubleRandomNotary").value(DEFAULT_DOUBLE_RANDOM_NOTARY.toString()))
            .andExpect(jsonPath("$.doubleRandomCompanyCondition1").value(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_1.toString()))
            .andExpect(jsonPath("$.doubleRandomCompanyCondition2").value(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_2.toString()))
            .andExpect(jsonPath("$.doubleRandomCompanyCondition3").value(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_3.toString()))
            .andExpect(jsonPath("$.doubleRandomCompanyCondition4").value(DEFAULT_DOUBLE_RANDOM_COMPANY_CONDITION_4.toString()))
            .andExpect(jsonPath("$.doubleRandomManagerCondition1").value(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_1.toString()))
            .andExpect(jsonPath("$.doubleRandomManagerCondition2").value(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_2.toString()))
            .andExpect(jsonPath("$.doubleRandomManagerCondition3").value(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_3.toString()))
            .andExpect(jsonPath("$.doubleRandomManagerCondition4").value(DEFAULT_DOUBLE_RANDOM_MANAGER_CONDITION_4.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDoubleRandom() throws Exception {
        // Get the doubleRandom
        restDoubleRandomMockMvc.perform(get("/api/double-randoms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDoubleRandom() throws Exception {
        // Initialize the database
        doubleRandomRepository.saveAndFlush(doubleRandom);
        int databaseSizeBeforeUpdate = doubleRandomRepository.findAll().size();

        // Update the doubleRandom
        DoubleRandom updatedDoubleRandom = doubleRandomRepository.findOne(doubleRandom.getId());
        updatedDoubleRandom
                .doubleRandomName(UPDATED_DOUBLE_RANDOM_NAME)
                .doubleRandomDate(UPDATED_DOUBLE_RANDOM_DATE)
                .doubleRandomNotary(UPDATED_DOUBLE_RANDOM_NOTARY)
                .doubleRandomCompanyCondition1(UPDATED_DOUBLE_RANDOM_COMPANY_CONDITION_1)
                .doubleRandomCompanyCondition2(UPDATED_DOUBLE_RANDOM_COMPANY_CONDITION_2)
                .doubleRandomCompanyCondition3(UPDATED_DOUBLE_RANDOM_COMPANY_CONDITION_3)
                .doubleRandomCompanyCondition4(UPDATED_DOUBLE_RANDOM_COMPANY_CONDITION_4)
                .doubleRandomManagerCondition1(UPDATED_DOUBLE_RANDOM_MANAGER_CONDITION_1)
                .doubleRandomManagerCondition2(UPDATED_DOUBLE_RANDOM_MANAGER_CONDITION_2)
                .doubleRandomManagerCondition3(UPDATED_DOUBLE_RANDOM_MANAGER_CONDITION_3)
                .doubleRandomManagerCondition4(UPDATED_DOUBLE_RANDOM_MANAGER_CONDITION_4);

        restDoubleRandomMockMvc.perform(put("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDoubleRandom)))
            .andExpect(status().isOk());

        // Validate the DoubleRandom in the database
        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeUpdate);
        DoubleRandom testDoubleRandom = doubleRandomList.get(doubleRandomList.size() - 1);
        assertThat(testDoubleRandom.getDoubleRandomName()).isEqualTo(UPDATED_DOUBLE_RANDOM_NAME);
        assertThat(testDoubleRandom.getDoubleRandomDate()).isEqualTo(UPDATED_DOUBLE_RANDOM_DATE);
        assertThat(testDoubleRandom.getDoubleRandomNotary()).isEqualTo(UPDATED_DOUBLE_RANDOM_NOTARY);
        assertThat(testDoubleRandom.getDoubleRandomCompanyCondition1()).isEqualTo(UPDATED_DOUBLE_RANDOM_COMPANY_CONDITION_1);
        assertThat(testDoubleRandom.getDoubleRandomCompanyCondition2()).isEqualTo(UPDATED_DOUBLE_RANDOM_COMPANY_CONDITION_2);
        assertThat(testDoubleRandom.getDoubleRandomCompanyCondition3()).isEqualTo(UPDATED_DOUBLE_RANDOM_COMPANY_CONDITION_3);
        assertThat(testDoubleRandom.getDoubleRandomCompanyCondition4()).isEqualTo(UPDATED_DOUBLE_RANDOM_COMPANY_CONDITION_4);
        assertThat(testDoubleRandom.getDoubleRandomManagerCondition1()).isEqualTo(UPDATED_DOUBLE_RANDOM_MANAGER_CONDITION_1);
        assertThat(testDoubleRandom.getDoubleRandomManagerCondition2()).isEqualTo(UPDATED_DOUBLE_RANDOM_MANAGER_CONDITION_2);
        assertThat(testDoubleRandom.getDoubleRandomManagerCondition3()).isEqualTo(UPDATED_DOUBLE_RANDOM_MANAGER_CONDITION_3);
        assertThat(testDoubleRandom.getDoubleRandomManagerCondition4()).isEqualTo(UPDATED_DOUBLE_RANDOM_MANAGER_CONDITION_4);
    }

    @Test
    @Transactional
    public void updateNonExistingDoubleRandom() throws Exception {
        int databaseSizeBeforeUpdate = doubleRandomRepository.findAll().size();

        // Create the DoubleRandom

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDoubleRandomMockMvc.perform(put("/api/double-randoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doubleRandom)))
            .andExpect(status().isCreated());

        // Validate the DoubleRandom in the database
        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDoubleRandom() throws Exception {
        // Initialize the database
        doubleRandomRepository.saveAndFlush(doubleRandom);
        int databaseSizeBeforeDelete = doubleRandomRepository.findAll().size();

        // Get the doubleRandom
        restDoubleRandomMockMvc.perform(delete("/api/double-randoms/{id}", doubleRandom.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DoubleRandom> doubleRandomList = doubleRandomRepository.findAll();
        assertThat(doubleRandomList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
