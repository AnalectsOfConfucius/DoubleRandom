package com.yyh.web.rest;

import com.yyh.DrApp;

import com.yyh.domain.Manager;
import com.yyh.repository.ManagerRepository;
import com.yyh.service.ManagerService;

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
 * Test class for the ManagerResource REST controller.
 *
 * @see ManagerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DrApp.class)
public class ManagerResourceIntTest {

    private static final String DEFAULT_MANAGER_ID = "AAAAAAAAAA";
    private static final String UPDATED_MANAGER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MANAGER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MANAGER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MANAGER_CARD_ID = "AAAAAAAAAA";
    private static final String UPDATED_MANAGER_CARD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MANAGER_CARD_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MANAGER_CARD_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MANAGER_SEX = "A";
    private static final String UPDATED_MANAGER_SEX = "B";

    private static final String DEFAULT_MANAGER_FLAG = "A";
    private static final String UPDATED_MANAGER_FLAG = "B";

    private static final Integer DEFAULT_CHECK_COUNT = 10000;
    private static final Integer UPDATED_CHECK_COUNT = 9999;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Inject
    private ManagerRepository managerRepository;

    @Inject
    private ManagerService managerService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restManagerMockMvc;

    private Manager manager;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ManagerResource managerResource = new ManagerResource();
        ReflectionTestUtils.setField(managerResource, "managerService", managerService);
        this.restManagerMockMvc = MockMvcBuilders.standaloneSetup(managerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Manager createEntity(EntityManager em) {
        Manager manager = new Manager()
                .managerId(DEFAULT_MANAGER_ID)
                .managerName(DEFAULT_MANAGER_NAME)
                .managerCardId(DEFAULT_MANAGER_CARD_ID)
                .managerCardType(DEFAULT_MANAGER_CARD_TYPE)
                .managerSex(DEFAULT_MANAGER_SEX)
                .managerFlag(DEFAULT_MANAGER_FLAG)
                .checkCount(DEFAULT_CHECK_COUNT)
                .description(DEFAULT_DESCRIPTION);
        return manager;
    }

    @Before
    public void initTest() {
        manager = createEntity(em);
    }

    @Test
    @Transactional
    public void createManager() throws Exception {
        int databaseSizeBeforeCreate = managerRepository.findAll().size();

        // Create the Manager

        restManagerMockMvc.perform(post("/api/managers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manager)))
            .andExpect(status().isCreated());

        // Validate the Manager in the database
        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeCreate + 1);
        Manager testManager = managerList.get(managerList.size() - 1);
        assertThat(testManager.getManagerId()).isEqualTo(DEFAULT_MANAGER_ID);
        assertThat(testManager.getManagerName()).isEqualTo(DEFAULT_MANAGER_NAME);
        assertThat(testManager.getManagerCardId()).isEqualTo(DEFAULT_MANAGER_CARD_ID);
        assertThat(testManager.getManagerCardType()).isEqualTo(DEFAULT_MANAGER_CARD_TYPE);
        assertThat(testManager.getManagerSex()).isEqualTo(DEFAULT_MANAGER_SEX);
        assertThat(testManager.getManagerFlag()).isEqualTo(DEFAULT_MANAGER_FLAG);
        assertThat(testManager.getCheckCount()).isEqualTo(DEFAULT_CHECK_COUNT);
        assertThat(testManager.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createManagerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = managerRepository.findAll().size();

        // Create the Manager with an existing ID
        Manager existingManager = new Manager();
        existingManager.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManagerMockMvc.perform(post("/api/managers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingManager)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkManagerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = managerRepository.findAll().size();
        // set the field null
        manager.setManagerId(null);

        // Create the Manager, which fails.

        restManagerMockMvc.perform(post("/api/managers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manager)))
            .andExpect(status().isBadRequest());

        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkManagerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = managerRepository.findAll().size();
        // set the field null
        manager.setManagerName(null);

        // Create the Manager, which fails.

        restManagerMockMvc.perform(post("/api/managers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manager)))
            .andExpect(status().isBadRequest());

        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkManagerCardIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = managerRepository.findAll().size();
        // set the field null
        manager.setManagerCardId(null);

        // Create the Manager, which fails.

        restManagerMockMvc.perform(post("/api/managers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manager)))
            .andExpect(status().isBadRequest());

        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkManagerCardTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = managerRepository.findAll().size();
        // set the field null
        manager.setManagerCardType(null);

        // Create the Manager, which fails.

        restManagerMockMvc.perform(post("/api/managers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manager)))
            .andExpect(status().isBadRequest());

        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkManagerSexIsRequired() throws Exception {
        int databaseSizeBeforeTest = managerRepository.findAll().size();
        // set the field null
        manager.setManagerSex(null);

        // Create the Manager, which fails.

        restManagerMockMvc.perform(post("/api/managers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manager)))
            .andExpect(status().isBadRequest());

        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllManagers() throws Exception {
        // Initialize the database
        managerRepository.saveAndFlush(manager);

        // Get all the managerList
        restManagerMockMvc.perform(get("/api/managers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manager.getId().intValue())))
            .andExpect(jsonPath("$.[*].managerId").value(hasItem(DEFAULT_MANAGER_ID.toString())))
            .andExpect(jsonPath("$.[*].managerName").value(hasItem(DEFAULT_MANAGER_NAME.toString())))
            .andExpect(jsonPath("$.[*].managerCardId").value(hasItem(DEFAULT_MANAGER_CARD_ID.toString())))
            .andExpect(jsonPath("$.[*].managerCardType").value(hasItem(DEFAULT_MANAGER_CARD_TYPE.toString())))
            .andExpect(jsonPath("$.[*].managerSex").value(hasItem(DEFAULT_MANAGER_SEX.toString())))
            .andExpect(jsonPath("$.[*].managerFlag").value(hasItem(DEFAULT_MANAGER_FLAG.toString())))
            .andExpect(jsonPath("$.[*].checkCount").value(hasItem(DEFAULT_CHECK_COUNT)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getManager() throws Exception {
        // Initialize the database
        managerRepository.saveAndFlush(manager);

        // Get the manager
        restManagerMockMvc.perform(get("/api/managers/{id}", manager.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(manager.getId().intValue()))
            .andExpect(jsonPath("$.managerId").value(DEFAULT_MANAGER_ID.toString()))
            .andExpect(jsonPath("$.managerName").value(DEFAULT_MANAGER_NAME.toString()))
            .andExpect(jsonPath("$.managerCardId").value(DEFAULT_MANAGER_CARD_ID.toString()))
            .andExpect(jsonPath("$.managerCardType").value(DEFAULT_MANAGER_CARD_TYPE.toString()))
            .andExpect(jsonPath("$.managerSex").value(DEFAULT_MANAGER_SEX.toString()))
            .andExpect(jsonPath("$.managerFlag").value(DEFAULT_MANAGER_FLAG.toString()))
            .andExpect(jsonPath("$.checkCount").value(DEFAULT_CHECK_COUNT))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingManager() throws Exception {
        // Get the manager
        restManagerMockMvc.perform(get("/api/managers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManager() throws Exception {
        // Initialize the database
        managerService.save(manager);

        int databaseSizeBeforeUpdate = managerRepository.findAll().size();

        // Update the manager
        Manager updatedManager = managerRepository.findOne(manager.getId());
        updatedManager
                .managerId(UPDATED_MANAGER_ID)
                .managerName(UPDATED_MANAGER_NAME)
                .managerCardId(UPDATED_MANAGER_CARD_ID)
                .managerCardType(UPDATED_MANAGER_CARD_TYPE)
                .managerSex(UPDATED_MANAGER_SEX)
                .managerFlag(UPDATED_MANAGER_FLAG)
                .checkCount(UPDATED_CHECK_COUNT)
                .description(UPDATED_DESCRIPTION);

        restManagerMockMvc.perform(put("/api/managers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedManager)))
            .andExpect(status().isOk());

        // Validate the Manager in the database
        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeUpdate);
        Manager testManager = managerList.get(managerList.size() - 1);
        assertThat(testManager.getManagerId()).isEqualTo(UPDATED_MANAGER_ID);
        assertThat(testManager.getManagerName()).isEqualTo(UPDATED_MANAGER_NAME);
        assertThat(testManager.getManagerCardId()).isEqualTo(UPDATED_MANAGER_CARD_ID);
        assertThat(testManager.getManagerCardType()).isEqualTo(UPDATED_MANAGER_CARD_TYPE);
        assertThat(testManager.getManagerSex()).isEqualTo(UPDATED_MANAGER_SEX);
        assertThat(testManager.getManagerFlag()).isEqualTo(UPDATED_MANAGER_FLAG);
        assertThat(testManager.getCheckCount()).isEqualTo(UPDATED_CHECK_COUNT);
        assertThat(testManager.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingManager() throws Exception {
        int databaseSizeBeforeUpdate = managerRepository.findAll().size();

        // Create the Manager

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restManagerMockMvc.perform(put("/api/managers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manager)))
            .andExpect(status().isCreated());

        // Validate the Manager in the database
        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteManager() throws Exception {
        // Initialize the database
        managerService.save(manager);

        int databaseSizeBeforeDelete = managerRepository.findAll().size();

        // Get the manager
        restManagerMockMvc.perform(delete("/api/managers/{id}", manager.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Manager> managerList = managerRepository.findAll();
        assertThat(managerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
