package com.pedro.testesunitarios;

public class RecurringAnticipationServiceTest {

//  package br.com.alelo.adquirencia.acqmerchantarv.service;
//
//import br.com.alelo.adquirencia.acqmerchantarv.MerchantArvProperties;
//import br.com.alelo.adquirencia.acqmerchantarv.entities.Channel;
//import br.com.alelo.adquirencia.acqmerchantarv.entities.RecurringAnticipation;
//import br.com.alelo.adquirencia.acqmerchantarv.entities.RecurringType;
//import br.com.alelo.adquirencia.acqmerchantarv.entities.WeekDay;
//import br.com.alelo.adquirencia.acqmerchantarv.exception.ArvFraudException;
//import br.com.alelo.adquirencia.acqmerchantarv.exception.DataNotFoundException;
//import br.com.alelo.adquirencia.acqmerchantarv.exception.RecurringAnticipationException;
//import br.com.alelo.adquirencia.acqmerchantarv.integration.model.ArvFraudVerificationResponse;
//import br.com.alelo.adquirencia.acqmerchantarv.mapper.ChannelMapper;
//import br.com.alelo.adquirencia.acqmerchantarv.mapper.RecurringAnticipationMapper;
//import br.com.alelo.adquirencia.acqmerchantarv.model.dto.ChannelDTO;
//import br.com.alelo.adquirencia.acqmerchantarv.model.dto.RecurringAnticipationDTO;
//import br.com.alelo.adquirencia.acqmerchantarv.model.dto.RecurringAnticipationRequestDTO;
//import br.com.alelo.adquirencia.acqmerchantarv.model.dto.RecurringTypeDTO;
//import br.com.alelo.adquirencia.acqmerchantarv.model.enums.ArvFraudCheckChannel;
//import br.com.alelo.adquirencia.acqmerchantarv.model.request.AuditInfo;
//import br.com.alelo.adquirencia.acqmerchantarv.repository.RecurringAnticipationRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.ChannelHelper.getChannel;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.RecurringAnticipationDTOHelper.getRecurringAnticipationDTO;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.RecurringAnticipationDTOHelper.getRecurringAnticipationDTOMensal;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.RecurringAnticipationHelper.getRecurringAnticipation;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.RecurringAnticipationHelper.getRecurringAnticipationMensal;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.RecurringAnticipationHelper.getRecurringAnticipationWithoutId;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.RecurringAnticipationRequestDTOHelper.getRecurringAnticipationRequestDTO;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.RecurringAnticipationRequestDTOHelper.getRecurringAnticipationRequestDTOMensalWithoutContractId;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.RecurringAnticipationRequestDTOHelper.getRecurringAnticipationRequestDTOWithoutContractId;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.RecurringTypeDTOHelper.getRecurringTypeDiariaDTO;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.RecurringTypeDTOHelper.getRecurringTypeMensalDTO;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.RecurringTypeHelper.getRecurringTypeDiaria;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.RecurringTypeHelper.getRecurringTypeMensal;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.RecurringTypeHelper.getRecurringTypeSemanal;
//import static br.com.alelo.adquirencia.acqmerchantarv.helper.WeekDayHelper.getWeekDay;
//import static br.com.alelo.adquirencia.acqmerchantarv.integration.enumerations.ArvVerificationStatus.REJECTED;
//import static br.com.alelo.adquirencia.acqmerchantarv.model.enums.ChannelEnum.FRONT_VENDAS;
//import static br.com.alelo.adquirencia.acqmerchantarv.model.enums.RecurrenceTypeEnum.DAILY;
//import static br.com.alelo.adquirencia.acqmerchantarv.model.enums.RiskCenterOperationType.ACTIVATE_RECURRING;
//import static br.com.alelo.adquirencia.acqmerchantarv.model.enums.RiskCenterOperationType.ALTER_RECURRING;
//import static br.com.alelo.adquirencia.acqmerchantarv.model.enums.RiskCenterOperationType.DEACTIVATE_RECURRING;
//import static java.util.Collections.singletonList;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyBoolean;
//import static org.mockito.ArgumentMatchers.anyList;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.atLeastOnce;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verifyNoInteractions;
//import static org.mockito.Mockito.when;
//
//  @ExtendWith(MockitoExtension.class)
//  class RecurringAnticipationServiceTest {
//
//    @InjectMocks
//    private RecurringAnticipationService recurringAnticipationService;
//
//    @Mock
//    private RecurringTypeService recurringTypeService;
//
//    @Mock
//    private ChannelService channelService;
//
//    @Mock
//    private WeekDayService weekDayService;
//
//    @Mock
//    private RecurringAnticipationRepository recurringAnticipationRepository;
//
//    @Mock
//    private RecurringAnticipationMapper recurringAnticipationMapperMock;
//
//    @Mock
//    private MerchantArvProperties merchantArvProperties;
//
//    @Mock
//    private RiskCenterService riskCenterService;
//
//    @Mock
//    private ChannelMapper channelMapper;
//
//    @Captor
//    private ArgumentCaptor<List<RecurringAnticipation>> captureSalveAllRecurring;
//
//    private static final String CNPJ = "58764890000198";
//
//    private static final long MERCHANT_ID = 123456L;
//
//    private RecurringAnticipationRequestDTO requestDTOWithContractId;
//
//    private RecurringAnticipation activeRecurringAnticipationContract;
//
//    private RecurringAnticipationDTO activeRecurringAnticipationContractDTO;
//
//    private RecurringAnticipation oldRecuringAnticipationContract;
//
//    private RecurringAnticipationDTO oldRecuringAnticipationContractDTO;
//
//    private RecurringAnticipationRequestDTO requestDTOWithoutContractId;
//
//    private RecurringAnticipation recurringAnticipationContractWithoutId;
//
//    private RecurringAnticipation recurringAnticipationContract;
//
//    private RecurringAnticipationDTO recurringAnticipationContractDTO;
//
//    private RecurringAnticipationDTO recurringAnticipationContractDTOWithDailyRT;
//
//    private RecurringAnticipation saveRecurring;
//
//    private Channel channel;
//
//    private RecurringType recurringTypeSemanal;
//
//    private RecurringType recurringTypeDiaria;
//
//    private WeekDay weekDay;
//
//    private ArvFraudVerificationResponse arvFraudVerificationResponse;
//
//    private AuditInfo auditInfo;
//
//    private ChannelDTO channelDTO;
//
//    @BeforeEach
//    public void setup() {
//      requestDTOWithContractId = getRecurringAnticipationRequestDTO();
//      activeRecurringAnticipationContract = getRecurringAnticipation(requestDTOWithContractId);
//      activeRecurringAnticipationContractDTO = getRecurringAnticipationDTO(activeRecurringAnticipationContract);
//      oldRecuringAnticipationContract = getRecurringAnticipation();
//      oldRecuringAnticipationContractDTO = getRecurringAnticipationDTO(oldRecuringAnticipationContract);
//      channel = getChannel();
//
//      requestDTOWithoutContractId = getRecurringAnticipationRequestDTOWithoutContractId();
//      recurringAnticipationContractWithoutId = getRecurringAnticipationWithoutId(requestDTOWithoutContractId);
//      recurringAnticipationContract = getRecurringAnticipation(recurringAnticipationContractWithoutId);
//      recurringAnticipationContractDTO = getRecurringAnticipationDTO(recurringAnticipationContract);
//      recurringTypeSemanal = getRecurringTypeSemanal();
//      recurringTypeDiaria = getRecurringTypeDiaria();
//      RecurringTypeDTO recurringTypeDiariaDTO = getRecurringTypeDiariaDTO();
//      recurringAnticipationContractDTOWithDailyRT = getRecurringAnticipationDTO(recurringAnticipationContract);
//      recurringAnticipationContractDTOWithDailyRT.setRecurringType(recurringTypeDiariaDTO);
//      recurringAnticipationContractDTOWithDailyRT.setWeekDay(null);
//      weekDay = getWeekDay();
//
//      saveRecurring = getRecurringAnticipation(activeRecurringAnticipationContractDTO);
//
//      arvFraudVerificationResponse = ArvFraudVerificationResponse.generateApprovedResponse();
//
//      auditInfo = AuditInfo.builder()
//          .channel(FRONT_VENDAS)
//          .build();
//
//      channelDTO = ChannelDTO.builder().build();
//    }
//
//    @Test
//    void shouldRollbackRecurringAnticipationContractIfContractIdIsFilled() throws ArvFraudException {
//      // Mocking getActiveRecurringAnticipationContract method
//      when(recurringAnticipationRepository.findByCnpjAndMerchantIdAndActiveContract(requestDTOWithContractId.getCnpj(),
//          requestDTOWithContractId.getMerchantId(), true))
//          .thenReturn(Optional.of(activeRecurringAnticipationContract));
//      when(recurringAnticipationRepository.findFirstByCnpjAndMerchantIdAndActiveContractOrderByUpdateDateDesc(
//          requestDTOWithContractId.getCnpj(), requestDTOWithContractId.getMerchantId(), false))
//          .thenReturn(Optional.of(oldRecuringAnticipationContract));
//      when(recurringAnticipationMapperMock.entityToDTO(any())).thenReturn(activeRecurringAnticipationContractDTO);
//
//      // Mocking setChannelProperties method
//      when(channelService.findChannelById(anyLong())).thenReturn(channel);
//
//      when(recurringAnticipationMapperMock.entityToDTO(any())).thenReturn(oldRecuringAnticipationContractDTO);
//
//      // Mocking saveRecurringAnticipationContractStatus method
//      when(recurringAnticipationMapperMock.dtoToEntity(any())).thenReturn(oldRecuringAnticipationContract);
//      when(recurringAnticipationRepository.save(any())).thenReturn(oldRecuringAnticipationContract);
//
//      RecurringAnticipationDTO dto = recurringAnticipationService.createRecurringAnticipation(requestDTOWithContractId).stream().findFirst().orElse(null);
//
//      assertNotNull(dto, "Should not be null!");
//      assertEquals(oldRecuringAnticipationContractDTO.getId(), dto.getId());
//      assertEquals(oldRecuringAnticipationContractDTO.getUpdateDate(), dto.getUpdateDate());
//      assertEquals(oldRecuringAnticipationContractDTO.getUpdateDate(), dto.getUpdateDate());
//      assertEquals(oldRecuringAnticipationContractDTO.getMonthDayId(), dto.getMonthDayId());
//      assertEquals(oldRecuringAnticipationContractDTO.getMerchantId(), dto.getMerchantId());
//      assertEquals(oldRecuringAnticipationContractDTO.getName(), dto.getName());
//      assertEquals(oldRecuringAnticipationContractDTO.getCpf(), dto.getCpf());
//      assertEquals(oldRecuringAnticipationContractDTO.getCnpj(), dto.getCnpj());
//      assertEquals(oldRecuringAnticipationContractDTO.getActiveContract(), dto.getActiveContract());
//      assertEquals(oldRecuringAnticipationContractDTO.getChannel().getId(), dto.getChannel().getId());
//      assertEquals(oldRecuringAnticipationContractDTO.getChannel().getDescription(), dto.getChannel().getDescription());
//      assertEquals(oldRecuringAnticipationContractDTO.getRecurringType().getId(), dto.getRecurringType().getId());
//      assertEquals(oldRecuringAnticipationContractDTO.getRecurringType().getDescription(), dto.getRecurringType().getDescription());
//      assertEquals(oldRecuringAnticipationContractDTO.getWeekDay().getId(), dto.getWeekDay().getId());
//      assertEquals(oldRecuringAnticipationContractDTO.getWeekDay().getDescription(), dto.getWeekDay().getDescription());
//
//      verify(recurringAnticipationRepository).deleteById(oldRecuringAnticipationContractDTO.getId());
//      verify(recurringAnticipationRepository).save(any());
//    }
//
//    @Test
//    void shouldReturnNullWhenRollbackRecurringAnticipationContract() throws ArvFraudException {
//      // Mocking getActiveRecurringAnticipationContract method
//      when(recurringAnticipationMapperMock.entityToDTO(any())).thenReturn(activeRecurringAnticipationContractDTO);
//
//      // Mocking setChannelProperties method
//      when(channelService.findChannelById(anyLong())).thenReturn(channel);
//
//      //Mocking getOldRecuringAnticipationContract method
//      when(recurringAnticipationRepository.findByCnpjAndMerchantIdAndActiveContract(requestDTOWithContractId.getCnpj(), requestDTOWithContractId.getMerchantId(), true))
//          .thenReturn(Optional.of(activeRecurringAnticipationContract));
//
//      when(recurringAnticipationMapperMock.entityToDTO(null)).thenReturn(null);
//
//      List<RecurringAnticipationDTO> dto = recurringAnticipationService.createRecurringAnticipation(requestDTOWithContractId);
//
//      assertNull(dto, "Should be null!");
//    }
//
//    @Test
//    void shouldCreateRecurringAnticipationContractWhenActiveContractNotExists() throws ArvFraudException {
//      // Mocking buildRecurringAnticipationToContract method ->requestDTOWithoutContractId
//      when(recurringAnticipationMapperMock.requestDTOToEntity(requestDTOWithoutContractId))
//          .thenReturn(recurringAnticipationContractWithoutId);
//      when(recurringTypeService.findRecurringTypeByDescription(anyString())).thenReturn(recurringTypeSemanal);
//      when(weekDayService.findWeekDayByDescription(anyString())).thenReturn(weekDay);
//      when(channelService.findChannelByDescription(any())).thenReturn(channel);
//      when(recurringAnticipationRepository.save(any())).thenReturn(recurringAnticipationContract);
//      when(recurringAnticipationMapperMock.entityToDTO(any())).thenReturn(recurringAnticipationContractDTO);
//
//      // Mocking setChannelProperties method
//      when(channelService.findChannelById(anyLong())).thenReturn(channel);
//
//      RecurringAnticipationDTO dto = recurringAnticipationService.createRecurringAnticipation(requestDTOWithoutContractId).stream().findFirst().orElse(null);
//
//      assertNotNull(dto, "Should not be null!");
//      assertEquals(recurringAnticipationContractDTO.getId(), dto.getId());
//      assertEquals(recurringAnticipationContractDTO.getUpdateDate(), dto.getUpdateDate());
//      assertEquals(recurringAnticipationContractDTO.getMonthDayId(), dto.getMonthDayId());
//      assertEquals(recurringAnticipationContractDTO.getMerchantId(), dto.getMerchantId());
//      assertEquals(recurringAnticipationContractDTO.getActiveContract(), dto.getActiveContract());
//      assertEquals(recurringAnticipationContractDTO.getChannel().getId(), dto.getChannel().getId());
//      assertEquals(recurringAnticipationContractDTO.getChannel().getDescription(), dto.getChannel().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getRecurringType().getId(), dto.getRecurringType().getId());
//      assertEquals(recurringAnticipationContractDTO.getRecurringType().getDescription(), dto.getRecurringType().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getWeekDay().getId(), dto.getWeekDay().getId());
//      assertEquals(recurringAnticipationContractDTO.getWeekDay().getDescription(), dto.getWeekDay().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getCnpj(), dto.getCnpj());
//      assertEquals(recurringAnticipationContractDTO.getName(), dto.getName());
//      assertEquals(recurringAnticipationContractDTO.getCpf(), dto.getCpf());
//    }
//
//    @Test
//    void shouldCreateRecurringAnticipationContractWhenActiveContractExists() throws ArvFraudException {
//      requestDTOWithoutContractId.setChannel(FRONT_VENDAS.getDescription());
//
//      when(recurringAnticipationRepository.findAllByCnpjAndMerchantIdAndActiveContract(requestDTOWithoutContractId.getCnpj(), requestDTOWithoutContractId.getMerchantId(), true))
//          .thenReturn(List.of(activeRecurringAnticipationContract));
//      when(recurringAnticipationMapperMock.entitiesToDTOsToList(any()))
//          .thenReturn(List.of(activeRecurringAnticipationContractDTO))
//          .thenReturn(List.of(recurringAnticipationContractDTO));
//      when(recurringAnticipationMapperMock.dtosToEntities(any()))
//          .thenReturn(List.of(saveRecurring));
//      when(recurringAnticipationRepository.save(any())).thenReturn(recurringAnticipationContract);
//      when(recurringAnticipationMapperMock.entityToDTO(any()))
//          .thenReturn(recurringAnticipationContractDTO);
//      when(channelService.findChannelById(anyLong())).thenReturn(channel);
//
//      // Mocking buildRecurringAnticipationToContract method ->requestDTOWithoutContractId
//      when(recurringAnticipationMapperMock.requestDTOToEntity(requestDTOWithoutContractId))
//          .thenReturn(recurringAnticipationContractWithoutId);
//      when(recurringTypeService.findRecurringTypeByDescription(recurringAnticipationContractWithoutId.getRecurringType().getDescription())).thenReturn(recurringTypeSemanal);
//      when(weekDayService.findWeekDayByDescription(recurringAnticipationContractWithoutId.getWeekDay().getDescription())).thenReturn(weekDay);
//      when(channelService.findChannelByDescription(requestDTOWithoutContractId.getChannel())).thenReturn(channel);
//      when(channelMapper.entityToDTO(any())).thenReturn(channelDTO);
//
//      RecurringAnticipationDTO dto = recurringAnticipationService.createRecurringAnticipation(requestDTOWithoutContractId).stream().findFirst().orElse(null);
//
//      assertNotNull(dto, "Should not be null!");
//      assertEquals(recurringAnticipationContractDTO.getId(), dto.getId());
//      assertEquals(recurringAnticipationContractDTO.getUpdateDate(), dto.getUpdateDate());
//      assertEquals(recurringAnticipationContractDTO.getMonthDayId(), dto.getMonthDayId());
//      assertEquals(recurringAnticipationContractDTO.getMerchantId(), dto.getMerchantId());
//      assertEquals(recurringAnticipationContractDTO.getActiveContract(), dto.getActiveContract());
//      assertEquals(recurringAnticipationContractDTO.getChannel().getId(), dto.getChannel().getId());
//      assertEquals(recurringAnticipationContractDTO.getChannel().getDescription(), dto.getChannel().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getRecurringType().getId(), dto.getRecurringType().getId());
//      assertEquals(recurringAnticipationContractDTO.getRecurringType().getDescription(), dto.getRecurringType().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getWeekDay().getId(), dto.getWeekDay().getId());
//      assertEquals(recurringAnticipationContractDTO.getWeekDay().getDescription(), dto.getWeekDay().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getCnpj(), dto.getCnpj());
//      assertEquals(recurringAnticipationContractDTO.getName(), dto.getName());
//      assertEquals(recurringAnticipationContractDTO.getCpf(), dto.getCpf());
//    }
//
//    @Test
//    void shouldCreateRecurringAnticipationContractWithDailyRecurringType() throws ArvFraudException {
//      // Mocking buildRecurringAnticipationToContract method ->requestDTOWithoutContractId
//      when(recurringAnticipationMapperMock.requestDTOToEntity(requestDTOWithoutContractId))
//          .thenReturn(recurringAnticipationContractWithoutId);
//      when(recurringTypeService.findRecurringTypeByDescription(anyString())).thenReturn(recurringTypeDiaria);
//      when(channelService.findChannelByDescription(any())).thenReturn(channel);
//      when(recurringAnticipationRepository.save(any())).thenReturn(recurringAnticipationContract);
//      when(recurringAnticipationMapperMock.entityToDTO(any())).thenReturn(recurringAnticipationContractDTOWithDailyRT);
//
//      // Mocking setChannelProperties method
//      when(channelService.findChannelById(anyLong())).thenReturn(channel);
//
//      RecurringAnticipationDTO dto = recurringAnticipationService.createRecurringAnticipation(requestDTOWithoutContractId).stream().findFirst().orElse(null);
//
//      assertNotNull(dto, "Should not be null!");
//      assertEquals(recurringAnticipationContractDTOWithDailyRT.getId(), dto.getId());
//      assertEquals(recurringAnticipationContractDTOWithDailyRT.getUpdateDate(), dto.getUpdateDate());
//      assertEquals(recurringAnticipationContractDTOWithDailyRT.getMonthDayId(), dto.getMonthDayId());
//      assertEquals(recurringAnticipationContractDTOWithDailyRT.getMerchantId(), dto.getMerchantId());
//      assertEquals(recurringAnticipationContractDTOWithDailyRT.getActiveContract(), dto.getActiveContract());
//      assertEquals(recurringAnticipationContractDTOWithDailyRT.getChannel().getId(), dto.getChannel().getId());
//      assertEquals(recurringAnticipationContractDTOWithDailyRT.getChannel().getDescription(), dto.getChannel().getDescription());
//      assertEquals(recurringAnticipationContractDTOWithDailyRT.getRecurringType().getId(), dto.getRecurringType().getId());
//      assertEquals(recurringAnticipationContractDTOWithDailyRT.getRecurringType().getDescription(), dto.getRecurringType().getDescription());
//      assertNull(dto.getWeekDay());
//      assertEquals(recurringAnticipationContractDTOWithDailyRT.getCnpj(), dto.getCnpj());
//      assertEquals(recurringAnticipationContractDTOWithDailyRT.getName(), dto.getName());
//      assertEquals(recurringAnticipationContractDTOWithDailyRT.getCpf(), dto.getCpf());
//
//      verify(recurringAnticipationRepository).save(any());
//      verify(recurringAnticipationMapperMock).requestDTOToEntity(any());
//      verify(recurringTypeService).findRecurringTypeByDescription(any());
//      verify(channelService).findChannelByDescription(any());
//      verify(recurringAnticipationMapperMock).entityToDTO(any());
//    }
//
//    @Test
//    void shouldCreateRecurringAnticipationContractWithMonthlyRecurringType() throws ArvFraudException {
//      // Mocking buildRecurringAnticipationToContract method ->requestDTOWithoutContractId
//      RecurringAnticipationRequestDTO requestDTOMensalWithoutContractId = getRecurringAnticipationRequestDTOMensalWithoutContractId();
//      RecurringType recurringTypeMensal = getRecurringTypeMensal();
//      RecurringAnticipation recurringAnticipation = getRecurringAnticipationMensal(requestDTOMensalWithoutContractId);
//      RecurringAnticipationDTO recurringAnticipationContractDTOWithMensalRT = getRecurringAnticipationDTOMensal(recurringAnticipation);
//      recurringAnticipationContractDTOWithMensalRT.setRecurringType(getRecurringTypeMensalDTO());
//      recurringAnticipationContractDTOWithMensalRT.setMonthDayId(1);
//
//      requestDTOMensalWithoutContractId.setChannel(FRONT_VENDAS.getDescription());
//
//      when(recurringAnticipationMapperMock.requestDTOToEntity(requestDTOMensalWithoutContractId))
//          .thenReturn(recurringAnticipation);
//      when(recurringTypeService.findRecurringTypeByDescription(anyString())).thenReturn(recurringTypeMensal);
//      when(channelService.findChannelByDescription(any())).thenReturn(channel);
//      when(recurringAnticipationRepository.saveAll(any())).thenReturn(List.of(recurringAnticipation));
//      when(recurringAnticipationMapperMock.entitiesToDTOsToList(any())).thenReturn(List.of(recurringAnticipationContractDTOWithMensalRT));
//      // Mocking setChannelProperties method
//      when(channelService.findChannelById(anyLong())).thenReturn(channel);
//      when(channelMapper.entityToDTO(any())).thenReturn(channelDTO);
//
//      RecurringAnticipationDTO dto = recurringAnticipationService.createRecurringAnticipation(requestDTOMensalWithoutContractId).stream().findFirst().orElse(null);
//
//      assertNotNull(dto, "Should not be null!");
//      assertEquals(recurringAnticipationContractDTOWithMensalRT.getId(), dto.getId());
//      assertEquals(recurringAnticipationContractDTOWithMensalRT.getUpdateDate(), dto.getUpdateDate());
//      assertEquals(recurringAnticipationContractDTOWithMensalRT.getMonthDayId(), dto.getMonthDayId());
//      assertEquals(recurringAnticipationContractDTOWithMensalRT.getMerchantId(), dto.getMerchantId());
//      assertEquals(recurringAnticipationContractDTOWithMensalRT.getActiveContract(), dto.getActiveContract());
//      assertEquals(recurringAnticipationContractDTOWithMensalRT.getChannel().getId(), dto.getChannel().getId());
//      assertEquals(recurringAnticipationContractDTOWithMensalRT.getChannel().getDescription(), dto.getChannel().getDescription());
//      assertEquals(recurringAnticipationContractDTOWithMensalRT.getRecurringType().getId(), dto.getRecurringType().getId());
//      assertEquals(recurringAnticipationContractDTOWithMensalRT.getRecurringType().getDescription(), dto.getRecurringType().getDescription());
//      assertNull(dto.getWeekDay());
//      assertEquals(recurringAnticipationContractDTOWithMensalRT.getCnpj(), dto.getCnpj());
//      assertEquals(recurringAnticipationContractDTOWithMensalRT.getName(), dto.getName());
//      assertEquals(recurringAnticipationContractDTOWithMensalRT.getCpf(), dto.getCpf());
//
//      verify(recurringAnticipationRepository, atLeastOnce()).saveAll(any());
//      verify(recurringAnticipationMapperMock, atLeastOnce()).requestDTOToEntity(any());
//      verify(recurringTypeService, atLeastOnce()).findRecurringTypeByDescription(any());
//      verify(recurringAnticipationMapperMock, atLeastOnce()).entitiesToDTOsToList(any());
//    }
//
//    @Test
//    void shouldThrowRecurringAnticipationExceptionWhenContractWithMonthlyRecurringTypeAndMoreThreeDays() {
//      RecurringAnticipationRequestDTO requestDTOMensalWithoutContractId = getRecurringAnticipationRequestDTOMensalWithoutContractId();
//      requestDTOMensalWithoutContractId.setRecurringMonthDays(List.of(1, 2, 3, 4));
//      assertThrows(RecurringAnticipationException.class, () -> recurringAnticipationService.createRecurringAnticipation(requestDTOMensalWithoutContractId));
//    }
//
//    @Test
//    void shouldThrowRecurringAnticipationExceptionWhenContractWithMonthlyRecurringTypeAndZeroDays() {
//      RecurringAnticipationRequestDTO requestDTOMensalWithoutContractId = getRecurringAnticipationRequestDTOMensalWithoutContractId();
//      requestDTOMensalWithoutContractId.setRecurringMonthDays(Collections.emptyList());
//      assertThrows(RecurringAnticipationException.class, () -> recurringAnticipationService.createRecurringAnticipation(requestDTOMensalWithoutContractId));
//    }
//
//    @Test
//    void shouldThrowRecurringAnticipationExceptionWhenContractWithMonthlyRecurringTypeAndNullDays() {
//      RecurringAnticipationRequestDTO requestDTOMensalWithoutContractId = getRecurringAnticipationRequestDTOMensalWithoutContractId();
//      requestDTOMensalWithoutContractId.setRecurringMonthDays(null);
//      assertThrows(RecurringAnticipationException.class, () -> recurringAnticipationService.createRecurringAnticipation(requestDTOMensalWithoutContractId));
//    }
//
//    @Test
//    void shouldSaveRecurringAnticipationContractWithTrueStatus() {
//      when(recurringAnticipationMapperMock.dtoToEntity(any())).thenReturn(oldRecuringAnticipationContract);
//      when(recurringAnticipationRepository.save(any())).thenReturn(oldRecuringAnticipationContract);
//
//      RecurringAnticipation recurringAnticipation =
//          recurringAnticipationService.saveRecurringAnticipationContractStatus(oldRecuringAnticipationContractDTO, true);
//
//      assertNotNull(recurringAnticipation, "Should not be null!");
//      assertEquals(oldRecuringAnticipationContract.getId(), recurringAnticipation.getId());
//      assertEquals(oldRecuringAnticipationContract.getUpdateDate(), recurringAnticipation.getUpdateDate());
//      assertEquals(oldRecuringAnticipationContract.getMonthDayId(), recurringAnticipation.getMonthDayId());
//      assertEquals(oldRecuringAnticipationContract.getMerchantId(), recurringAnticipation.getMerchantId());
//      assertEquals(oldRecuringAnticipationContract.getName(), recurringAnticipation.getName());
//      assertEquals(oldRecuringAnticipationContract.getCpf(), recurringAnticipation.getCpf());
//      assertEquals(oldRecuringAnticipationContract.getCnpj(), recurringAnticipation.getCnpj());
//      assertTrue(oldRecuringAnticipationContract.getActiveContract());
//      assertEquals(oldRecuringAnticipationContract.getChannelId(), recurringAnticipation.getChannelId());
//      assertEquals(oldRecuringAnticipationContract.getRecurringType().getId(), recurringAnticipation.getRecurringType().getId());
//      assertEquals(oldRecuringAnticipationContract.getRecurringType().getDescription(), recurringAnticipation.getRecurringType().getDescription());
//      assertEquals(oldRecuringAnticipationContract.getWeekDay().getId(), recurringAnticipation.getWeekDay().getId());
//      assertEquals(oldRecuringAnticipationContract.getWeekDay().getDescription(), recurringAnticipation.getWeekDay().getDescription());
//
//      verify(recurringAnticipationMapperMock).dtoToEntity(any());
//      verify(recurringAnticipationRepository).save(any());
//    }
//
//    @Test
//    void shouldSaveRecurringAnticipationContractWithFalseStatus() {
//      when(recurringAnticipationMapperMock.dtoToEntity(any())).thenReturn(oldRecuringAnticipationContract);
//      when(recurringAnticipationRepository.save(any())).thenReturn(oldRecuringAnticipationContract);
//
//      RecurringAnticipation recurringAnticipation =
//          recurringAnticipationService.saveRecurringAnticipationContractStatus(oldRecuringAnticipationContractDTO, false);
//
//      assertNotNull(recurringAnticipation, "Should not be null!");
//      assertEquals(oldRecuringAnticipationContract.getId(), recurringAnticipation.getId());
//      assertEquals(oldRecuringAnticipationContract.getUpdateDate(), recurringAnticipation.getUpdateDate());
//      assertEquals(oldRecuringAnticipationContract.getMonthDayId(), recurringAnticipation.getMonthDayId());
//      assertEquals(oldRecuringAnticipationContract.getMerchantId(), recurringAnticipation.getMerchantId());
//      assertEquals(oldRecuringAnticipationContract.getName(), recurringAnticipation.getName());
//      assertEquals(oldRecuringAnticipationContract.getCpf(), recurringAnticipation.getCpf());
//      assertEquals(oldRecuringAnticipationContract.getCnpj(), recurringAnticipation.getCnpj());
//      assertFalse(oldRecuringAnticipationContract.getActiveContract());
//      assertEquals(oldRecuringAnticipationContract.getChannelId(), recurringAnticipation.getChannelId());
//      assertEquals(oldRecuringAnticipationContract.getRecurringType().getId(), recurringAnticipation.getRecurringType().getId());
//      assertEquals(oldRecuringAnticipationContract.getRecurringType().getDescription(), recurringAnticipation.getRecurringType().getDescription());
//      assertEquals(oldRecuringAnticipationContract.getWeekDay().getId(), recurringAnticipation.getWeekDay().getId());
//      assertEquals(oldRecuringAnticipationContract.getWeekDay().getDescription(), recurringAnticipation.getWeekDay().getDescription());
//
//      verify(recurringAnticipationMapperMock).dtoToEntity(any());
//      verify(recurringAnticipationRepository).save(any());
//    }
//
//    @Test
//    void shouldDeleteRecurringAnticipationContract() {
//      // Mocking getActiveRecurringAnticipationContract method
//      when(recurringAnticipationRepository.findByCnpjAndMerchantIdAndActiveContract(anyString(), anyLong(), anyBoolean()))
//          .thenReturn(Optional.of(activeRecurringAnticipationContract));
//      when(recurringAnticipationMapperMock.entityToDTO(any())).thenReturn(activeRecurringAnticipationContractDTO);
//
//      // Mocking setChannelProperties method
//      when(channelService.findChannelById(anyLong())).thenReturn(channel);
//
//      recurringAnticipationService.deleteRecurringAnticipationContract(CNPJ, MERCHANT_ID);
//
//      verify(recurringAnticipationRepository).deleteById(activeRecurringAnticipationContractDTO.getId());
//    }
//
//    @Test
//    void shouldThrowDataNotFoundExceptionWhenDeleteRecurringAnticipationContract() {
//      assertThrows(DataNotFoundException.class, () -> recurringAnticipationService.deleteRecurringAnticipationContract(CNPJ, MERCHANT_ID));
//    }
//
//    @Test
//    void shouldGetOldRecurringAnticipationContract() {
//      when(recurringAnticipationRepository
//          .findFirstByCnpjAndMerchantIdAndActiveContractOrderByUpdateDateDesc(CNPJ, MERCHANT_ID, false))
//          .thenReturn(Optional.of(oldRecuringAnticipationContract));
//
//      RecurringAnticipation recurringAnticipation = recurringAnticipationService.getOldRecurringAnticipationContract(CNPJ, MERCHANT_ID);
//
//      assertNotNull(recurringAnticipation, "Should not be null!");
//      assertEquals(oldRecuringAnticipationContract.getId(), recurringAnticipation.getId());
//      assertEquals(oldRecuringAnticipationContract.getCnpj(), recurringAnticipation.getCnpj());
//      assertEquals(oldRecuringAnticipationContract.getCpf(), recurringAnticipation.getCpf());
//      assertEquals(oldRecuringAnticipationContract.getName(), recurringAnticipation.getName());
//      assertEquals(oldRecuringAnticipationContract.getWeekDay().getId(), recurringAnticipation.getWeekDay().getId());
//      assertEquals(oldRecuringAnticipationContract.getWeekDay().getDescription(), recurringAnticipation.getWeekDay().getDescription());
//      assertEquals(oldRecuringAnticipationContract.getRecurringType().getId(), recurringAnticipation.getRecurringType().getId());
//      assertEquals(oldRecuringAnticipationContract.getRecurringType().getDescription(), recurringAnticipation.getRecurringType().getDescription());
//      assertEquals(oldRecuringAnticipationContract.getChannelId(), recurringAnticipation.getChannelId());
//      assertEquals(oldRecuringAnticipationContract.getActiveContract(), recurringAnticipation.getActiveContract());
//      assertEquals(oldRecuringAnticipationContract.getMerchantId(), recurringAnticipation.getMerchantId());
//      assertEquals(oldRecuringAnticipationContract.getMonthDayId(), recurringAnticipation.getMonthDayId());
//      assertEquals(oldRecuringAnticipationContract.getUpdateDate(), recurringAnticipation.getUpdateDate());
//    }
//
//    @Test
//    void shouldReturnNullWhenGetOldRecurringAnticipationContract() {
//      when(recurringAnticipationRepository
//          .findFirstByCnpjAndMerchantIdAndActiveContractOrderByUpdateDateDesc(anyString(), anyLong(), anyBoolean()))
//          .thenReturn(Optional.empty());
//
//      RecurringAnticipation recurringAnticipation = recurringAnticipationService.getOldRecurringAnticipationContract(CNPJ, MERCHANT_ID);
//
//      assertNull(recurringAnticipation, "Should be null!");
//    }
//
//    @Test
//    void shouldInactivateAllGivenCnpj() {
//      when(recurringAnticipationRepository.findAllByCnpjAndActiveContract(anyString(), anyBoolean())).thenReturn(singletonList(recurringAnticipationContract));
//      when(recurringAnticipationRepository.saveAll(anyList())).thenReturn(new ArrayList<>());
//
//      recurringAnticipationService.inactivateAll(CNPJ);
//
//      verify(recurringAnticipationRepository).findAllByCnpjAndActiveContract(CNPJ, true);
//      verify(recurringAnticipationRepository).saveAll(captureSalveAllRecurring.capture());
//
//      List<RecurringAnticipation> captureList = captureSalveAllRecurring.getValue();
//
//      captureList.forEach(recurring -> assertFalse(recurring.getActiveContract()));
//    }
//
//    @Test
//    void shouldGetActiveRecurringAnticipationContractByCnpjAndMerchantId() {
//      // Mocking getActiveRecurringAnticipationContract method
//      when(recurringAnticipationRepository.findByCnpjAndMerchantIdAndActiveContract(CNPJ, MERCHANT_ID, true))
//          .thenReturn(Optional.of(activeRecurringAnticipationContract));
//      when(recurringAnticipationMapperMock.entityToDTO(activeRecurringAnticipationContract)).thenReturn(activeRecurringAnticipationContractDTO);
//
//      // Mocking setChannelProperties method
//      when(channelService.findChannelById(activeRecurringAnticipationContractDTO.getChannel().getId())).thenReturn(channel);
//
//      RecurringAnticipationDTO dto = recurringAnticipationService.getActiveRecurringAnticipationContract(CNPJ, MERCHANT_ID);
//
//      assertNotNull(dto, "Should not be null!");
//      assertEquals(activeRecurringAnticipationContractDTO.getId(), dto.getId());
//      assertEquals(activeRecurringAnticipationContractDTO.getCpf(), dto.getCpf());
//      assertEquals(activeRecurringAnticipationContractDTO.getName(), dto.getName());
//      assertEquals(activeRecurringAnticipationContractDTO.getCnpj(), dto.getCnpj());
//      assertEquals(activeRecurringAnticipationContractDTO.getWeekDay().getId(), dto.getWeekDay().getId());
//      assertEquals(activeRecurringAnticipationContractDTO.getWeekDay().getDescription(), dto.getWeekDay().getDescription());
//      assertEquals(activeRecurringAnticipationContractDTO.getRecurringType().getId(), dto.getRecurringType().getId());
//      assertEquals(activeRecurringAnticipationContractDTO.getRecurringType().getDescription(), dto.getRecurringType().getDescription());
//      assertEquals(activeRecurringAnticipationContractDTO.getChannel().getId(), dto.getChannel().getId());
//      assertEquals(activeRecurringAnticipationContractDTO.getChannel().getDescription(), dto.getChannel().getDescription());
//      assertEquals(activeRecurringAnticipationContractDTO.getActiveContract(), dto.getActiveContract());
//      assertEquals(activeRecurringAnticipationContractDTO.getMerchantId(), dto.getMerchantId());
//      assertEquals(activeRecurringAnticipationContractDTO.getMonthDayId(), dto.getMonthDayId());
//      assertEquals(activeRecurringAnticipationContractDTO.getUpdateDate(), dto.getUpdateDate());
//    }
//
//    @Test
//    void shouldInactivateRecurringAnticipationContractsByCnpjAndMerchantId() throws ArvFraudException {
//      List<RecurringAnticipation> activeRecurringAnticipationContracts = List.of(activeRecurringAnticipationContract);
//      List<RecurringAnticipationDTO> activeRecurringAnticipationContractDTO = List.of(this.activeRecurringAnticipationContractDTO);
//      when(recurringAnticipationRepository.findAllByCnpjAndMerchantIdAndActiveContract(CNPJ, MERCHANT_ID, true))
//          .thenReturn(activeRecurringAnticipationContracts);
//      when(recurringAnticipationMapperMock.entitiesToDTOsToList(activeRecurringAnticipationContracts)).thenReturn(activeRecurringAnticipationContractDTO);
//
//      // Mocking setChannelProperties method
//      when(channelService.findChannelById(this.activeRecurringAnticipationContractDTO.getChannel().getId())).thenReturn(channel);
//
//      when(recurringAnticipationMapperMock.dtosToEntities(any())).thenReturn(activeRecurringAnticipationContracts);
//      when(recurringAnticipationRepository.saveAll(activeRecurringAnticipationContracts)).thenReturn(activeRecurringAnticipationContracts);
//      when(channelMapper.entityToDTO(any())).thenReturn(channelDTO);
//
//      List<RecurringAnticipationDTO> dto = recurringAnticipationService.inactivateRecurringAnticipationContracts(CNPJ, MERCHANT_ID, true, auditInfo);
//
//      assertNotNull(dto, "Should not be null!");
//      assertFalse(dto.isEmpty(), "Should not be empty!");
//
//      verify(recurringAnticipationMapperMock).dtosToEntities(activeRecurringAnticipationContractDTO);
//      verify(recurringAnticipationRepository).saveAll(activeRecurringAnticipationContracts);
//    }
//
//    @Test
//    void shouldThrowDataNotFoundExceptionWhenNotExistsInactivateRecurringAnticipationContracts() {
//      List<RecurringAnticipation> activeRecurringAnticipationContracts = Collections.emptyList();
//      List<RecurringAnticipationDTO> activeRecurringAnticipationContractDTO = Collections.emptyList();
//      auditInfo.setChannel(FRONT_VENDAS);
//
//      when(recurringAnticipationRepository.findAllByCnpjAndMerchantIdAndActiveContract(CNPJ, MERCHANT_ID, true))
//          .thenReturn(activeRecurringAnticipationContracts);
//      when(recurringAnticipationMapperMock.entitiesToDTOsToList(activeRecurringAnticipationContracts)).thenReturn(activeRecurringAnticipationContractDTO);
//
//      assertThrows(DataNotFoundException.class,
//          () -> recurringAnticipationService.inactivateRecurringAnticipationContracts(CNPJ, MERCHANT_ID, true, auditInfo));
//
//    }
//
//    @Test
//    void shouldCheckForFraudOnContractWhenActiveContractExists() throws ArvFraudException {
//      final String AMERICA_SAO_PAULO = "America/Sao_Paulo";
//
//      requestDTOWithoutContractId.setCheckForFraud(true);
//      requestDTOWithoutContractId.setChannel(FRONT_VENDAS.getDescription());
//      requestDTOWithoutContractId.setRecurringType(DAILY.getDescription());
//
//      when(recurringAnticipationRepository.findAllByCnpjAndMerchantIdAndActiveContract(requestDTOWithoutContractId.getCnpj(), requestDTOWithoutContractId.getMerchantId(), true))
//          .thenReturn(List.of(activeRecurringAnticipationContract));
//      when(recurringAnticipationMapperMock.entitiesToDTOsToList(any()))
//          .thenReturn(List.of(activeRecurringAnticipationContractDTO))
//          .thenReturn(List.of(recurringAnticipationContractDTO));
//      when(recurringAnticipationMapperMock.dtosToEntities(any()))
//          .thenReturn(List.of(saveRecurring));
//      when(recurringAnticipationRepository.save(any())).thenReturn(recurringAnticipationContract);
//      when(recurringAnticipationMapperMock.entityToDTO(any()))
//          .thenReturn(recurringAnticipationContractDTO);
//      when(channelService.findChannelById(anyLong())).thenReturn(channel);
//      when(channelMapper.entityToDTO(any())).thenReturn(channelDTO);
//
//      when(recurringAnticipationMapperMock.requestDTOToEntity(requestDTOWithoutContractId))
//          .thenReturn(recurringAnticipationContractWithoutId);
//      when(recurringTypeService.findRecurringTypeByDescription(recurringAnticipationContractWithoutId.getRecurringType().getDescription())).thenReturn(recurringTypeSemanal);
//      when(weekDayService.findWeekDayByDescription(recurringAnticipationContractWithoutId.getWeekDay().getDescription())).thenReturn(weekDay);
//      when(channelService.findChannelByDescription(requestDTOWithoutContractId.getChannel())).thenReturn(channel);
//      when(merchantArvProperties.getRiskCenterIntegrationEnabled()).thenReturn(true);
//      when(riskCenterService.checkArvForFraud(requestDTOWithoutContractId.getMerchantId(), requestDTOWithoutContractId.getCnpj(), ArvFraudCheckChannel.FRONT_VENDAS, ALTER_RECURRING, DAILY)).thenReturn(arvFraudVerificationResponse);
//
//      RecurringAnticipationDTO dto = recurringAnticipationService.createRecurringAnticipation(requestDTOWithoutContractId).stream().findFirst().orElse(null);
//
//      assertNotNull(dto, "Should not be null!");
//      assertEquals(recurringAnticipationContractDTO.getId(), dto.getId());
//      assertEquals(recurringAnticipationContractDTO.getUpdateDate(), dto.getUpdateDate());
//      assertEquals(recurringAnticipationContractDTO.getMonthDayId(), dto.getMonthDayId());
//      assertEquals(recurringAnticipationContractDTO.getMerchantId(), dto.getMerchantId());
//      assertEquals(recurringAnticipationContractDTO.getActiveContract(), dto.getActiveContract());
//      assertEquals(recurringAnticipationContractDTO.getChannel().getId(), dto.getChannel().getId());
//      assertEquals(recurringAnticipationContractDTO.getChannel().getDescription(), dto.getChannel().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getRecurringType().getId(), dto.getRecurringType().getId());
//      assertEquals(recurringAnticipationContractDTO.getRecurringType().getDescription(), dto.getRecurringType().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getWeekDay().getId(), dto.getWeekDay().getId());
//      assertEquals(recurringAnticipationContractDTO.getWeekDay().getDescription(), dto.getWeekDay().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getCnpj(), dto.getCnpj());
//      assertEquals(recurringAnticipationContractDTO.getName(), dto.getName());
//      assertEquals(recurringAnticipationContractDTO.getCpf(), dto.getCpf());
//    }
//
//    @Test
//    void shouldCheckForFraudOnContractWhenActiveContractDoesNotExists() throws ArvFraudException {
//      final String AMERICA_SAO_PAULO = "America/Sao_Paulo";
//
//      requestDTOWithoutContractId.setCheckForFraud(true);
//      requestDTOWithoutContractId.setChannel(FRONT_VENDAS.getDescription());
//      requestDTOWithoutContractId.setRecurringType(DAILY.getDescription());
//
//      when(recurringAnticipationRepository.findAllByCnpjAndMerchantIdAndActiveContract(requestDTOWithoutContractId.getCnpj(), requestDTOWithoutContractId.getMerchantId(), true))
//          .thenReturn(Collections.emptyList());
//      when(recurringAnticipationMapperMock.entitiesToDTOsToList(any()))
//          .thenReturn(Collections.emptyList());
//      when(recurringAnticipationRepository.save(any())).thenReturn(recurringAnticipationContract);
//      when(recurringAnticipationMapperMock.entityToDTO(any()))
//          .thenReturn(recurringAnticipationContractDTO);
//      when(channelService.findChannelById(anyLong())).thenReturn(channel);
//
//      when(recurringAnticipationMapperMock.requestDTOToEntity(requestDTOWithoutContractId))
//          .thenReturn(recurringAnticipationContractWithoutId);
//      when(recurringTypeService.findRecurringTypeByDescription(recurringAnticipationContractWithoutId.getRecurringType().getDescription())).thenReturn(recurringTypeSemanal);
//      when(weekDayService.findWeekDayByDescription(recurringAnticipationContractWithoutId.getWeekDay().getDescription())).thenReturn(weekDay);
//      when(channelService.findChannelByDescription(requestDTOWithoutContractId.getChannel())).thenReturn(channel);
//      when(merchantArvProperties.getRiskCenterIntegrationEnabled()).thenReturn(true);
//      when(riskCenterService.checkArvForFraud(requestDTOWithoutContractId.getMerchantId(), requestDTOWithoutContractId.getCnpj(), ArvFraudCheckChannel.FRONT_VENDAS, ACTIVATE_RECURRING, DAILY)).thenReturn(arvFraudVerificationResponse);
//
//      RecurringAnticipationDTO dto = recurringAnticipationService.createRecurringAnticipation(requestDTOWithoutContractId).stream().findFirst().orElse(null);
//
//      assertNotNull(dto, "Should not be null!");
//      assertEquals(recurringAnticipationContractDTO.getId(), dto.getId());
//      assertEquals(recurringAnticipationContractDTO.getUpdateDate(), dto.getUpdateDate());
//      assertEquals(recurringAnticipationContractDTO.getMonthDayId(), dto.getMonthDayId());
//      assertEquals(recurringAnticipationContractDTO.getMerchantId(), dto.getMerchantId());
//      assertEquals(recurringAnticipationContractDTO.getActiveContract(), dto.getActiveContract());
//      assertEquals(recurringAnticipationContractDTO.getChannel().getId(), dto.getChannel().getId());
//      assertEquals(recurringAnticipationContractDTO.getChannel().getDescription(), dto.getChannel().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getRecurringType().getId(), dto.getRecurringType().getId());
//      assertEquals(recurringAnticipationContractDTO.getRecurringType().getDescription(), dto.getRecurringType().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getWeekDay().getId(), dto.getWeekDay().getId());
//      assertEquals(recurringAnticipationContractDTO.getWeekDay().getDescription(), dto.getWeekDay().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getCnpj(), dto.getCnpj());
//      assertEquals(recurringAnticipationContractDTO.getName(), dto.getName());
//      assertEquals(recurringAnticipationContractDTO.getCpf(), dto.getCpf());
//    }
//
//    @Test
//    void shouldNotCheckForFraudOnContractWhenCheckForFraudIsFalse() throws ArvFraudException {
//
//      requestDTOWithoutContractId.setChannel(FRONT_VENDAS.getDescription());
//      requestDTOWithoutContractId.setRecurringType(DAILY.getDescription());
//
//      when(recurringAnticipationRepository.findAllByCnpjAndMerchantIdAndActiveContract(requestDTOWithoutContractId.getCnpj(), requestDTOWithoutContractId.getMerchantId(), true))
//          .thenReturn(List.of(activeRecurringAnticipationContract));
//      when(recurringAnticipationMapperMock.entitiesToDTOsToList(any()))
//          .thenReturn(List.of(activeRecurringAnticipationContractDTO))
//          .thenReturn(List.of(recurringAnticipationContractDTO));
//      when(recurringAnticipationMapperMock.dtosToEntities(any()))
//          .thenReturn(List.of(saveRecurring));
//      when(recurringAnticipationRepository.save(any())).thenReturn(recurringAnticipationContract);
//      when(recurringAnticipationMapperMock.entityToDTO(any()))
//          .thenReturn(recurringAnticipationContractDTO);
//      when(channelService.findChannelById(anyLong())).thenReturn(channel);
//
//      when(recurringAnticipationMapperMock.requestDTOToEntity(requestDTOWithoutContractId))
//          .thenReturn(recurringAnticipationContractWithoutId);
//      when(recurringTypeService.findRecurringTypeByDescription(recurringAnticipationContractWithoutId.getRecurringType().getDescription())).thenReturn(recurringTypeSemanal);
//      when(weekDayService.findWeekDayByDescription(recurringAnticipationContractWithoutId.getWeekDay().getDescription())).thenReturn(weekDay);
//      when(channelService.findChannelByDescription(requestDTOWithoutContractId.getChannel())).thenReturn(channel);
//      when(merchantArvProperties.getRiskCenterIntegrationEnabled()).thenReturn(true);
//
//      RecurringAnticipationDTO dto = recurringAnticipationService.createRecurringAnticipation(requestDTOWithoutContractId).stream().findFirst().orElse(null);
//
//      verifyNoInteractions(riskCenterService);
//      assertNotNull(dto, "Should not be null!");
//      assertEquals(recurringAnticipationContractDTO.getId(), dto.getId());
//      assertEquals(recurringAnticipationContractDTO.getUpdateDate(), dto.getUpdateDate());
//      assertEquals(recurringAnticipationContractDTO.getMonthDayId(), dto.getMonthDayId());
//      assertEquals(recurringAnticipationContractDTO.getMerchantId(), dto.getMerchantId());
//      assertEquals(recurringAnticipationContractDTO.getActiveContract(), dto.getActiveContract());
//      assertEquals(recurringAnticipationContractDTO.getChannel().getId(), dto.getChannel().getId());
//      assertEquals(recurringAnticipationContractDTO.getChannel().getDescription(), dto.getChannel().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getRecurringType().getId(), dto.getRecurringType().getId());
//      assertEquals(recurringAnticipationContractDTO.getRecurringType().getDescription(), dto.getRecurringType().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getWeekDay().getId(), dto.getWeekDay().getId());
//      assertEquals(recurringAnticipationContractDTO.getWeekDay().getDescription(), dto.getWeekDay().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getCnpj(), dto.getCnpj());
//      assertEquals(recurringAnticipationContractDTO.getName(), dto.getName());
//      assertEquals(recurringAnticipationContractDTO.getCpf(), dto.getCpf());
//    }
//
//    @Test
//    void shouldNotThrowGenericException() throws ArvFraudException {
//      final String AMERICA_SAO_PAULO = "America/Sao_Paulo";
//
//      requestDTOWithoutContractId.setCheckForFraud(true);
//      requestDTOWithoutContractId.setChannel(FRONT_VENDAS.getDescription());
//      requestDTOWithoutContractId.setRecurringType(DAILY.getDescription());
//
//      when(recurringAnticipationRepository.findAllByCnpjAndMerchantIdAndActiveContract(requestDTOWithoutContractId.getCnpj(), requestDTOWithoutContractId.getMerchantId(), true))
//          .thenReturn(List.of(activeRecurringAnticipationContract));
//      when(recurringAnticipationMapperMock.entitiesToDTOsToList(any()))
//          .thenReturn(List.of(activeRecurringAnticipationContractDTO))
//          .thenReturn(List.of(recurringAnticipationContractDTO));
//      when(recurringAnticipationMapperMock.dtosToEntities(any()))
//          .thenReturn(List.of(saveRecurring));
//      when(recurringAnticipationRepository.save(any())).thenReturn(recurringAnticipationContract);
//      when(recurringAnticipationMapperMock.entityToDTO(any()))
//          .thenReturn(recurringAnticipationContractDTO);
//      when(channelService.findChannelById(anyLong())).thenReturn(channel);
//
//      when(recurringAnticipationMapperMock.requestDTOToEntity(requestDTOWithoutContractId))
//          .thenReturn(recurringAnticipationContractWithoutId);
//      when(recurringTypeService.findRecurringTypeByDescription(recurringAnticipationContractWithoutId.getRecurringType().getDescription())).thenReturn(recurringTypeSemanal);
//      when(weekDayService.findWeekDayByDescription(recurringAnticipationContractWithoutId.getWeekDay().getDescription())).thenReturn(weekDay);
//      when(channelService.findChannelByDescription(requestDTOWithoutContractId.getChannel())).thenReturn(channel);
//      when(merchantArvProperties.getRiskCenterIntegrationEnabled()).thenReturn(true);
//      when(channelMapper.entityToDTO(any())).thenReturn(channelDTO);
//      doThrow(NullPointerException.class).when(riskCenterService).checkArvForFraud(requestDTOWithoutContractId.getMerchantId(), requestDTOWithoutContractId.getCnpj(), ArvFraudCheckChannel.FRONT_VENDAS, ALTER_RECURRING, DAILY);
//
//      RecurringAnticipationDTO dto = recurringAnticipationService.createRecurringAnticipation(requestDTOWithoutContractId).stream().findFirst().orElse(null);
//
//      assertNotNull(dto, "Should not be null!");
//      assertEquals(recurringAnticipationContractDTO.getId(), dto.getId());
//      assertEquals(recurringAnticipationContractDTO.getUpdateDate(), dto.getUpdateDate());
//      assertEquals(recurringAnticipationContractDTO.getMonthDayId(), dto.getMonthDayId());
//      assertEquals(recurringAnticipationContractDTO.getMerchantId(), dto.getMerchantId());
//      assertEquals(recurringAnticipationContractDTO.getActiveContract(), dto.getActiveContract());
//      assertEquals(recurringAnticipationContractDTO.getChannel().getId(), dto.getChannel().getId());
//      assertEquals(recurringAnticipationContractDTO.getChannel().getDescription(), dto.getChannel().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getRecurringType().getId(), dto.getRecurringType().getId());
//      assertEquals(recurringAnticipationContractDTO.getRecurringType().getDescription(), dto.getRecurringType().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getWeekDay().getId(), dto.getWeekDay().getId());
//      assertEquals(recurringAnticipationContractDTO.getWeekDay().getDescription(), dto.getWeekDay().getDescription());
//      assertEquals(recurringAnticipationContractDTO.getCnpj(), dto.getCnpj());
//      assertEquals(recurringAnticipationContractDTO.getName(), dto.getName());
//      assertEquals(recurringAnticipationContractDTO.getCpf(), dto.getCpf());
//    }
//
//    @Test
//    void shouldBlockRecurrenceWhenFraudIsDetected() {
//
//      requestDTOWithoutContractId.setCheckForFraud(true);
//      requestDTOWithoutContractId.setChannel(FRONT_VENDAS.getDescription());
//      requestDTOWithoutContractId.setRecurringType(DAILY.getDescription());
//
//      when(recurringAnticipationRepository.findAllByCnpjAndMerchantIdAndActiveContract(requestDTOWithoutContractId.getCnpj(), requestDTOWithoutContractId.getMerchantId(), true))
//          .thenReturn(List.of(activeRecurringAnticipationContract));
//      when(recurringAnticipationMapperMock.entitiesToDTOsToList(any()))
//          .thenReturn(List.of(activeRecurringAnticipationContractDTO))
//          .thenReturn(List.of(recurringAnticipationContractDTO));
//      when(channelService.findChannelById(anyLong())).thenReturn(channel);
//      when(merchantArvProperties.getRiskCenterIntegrationEnabled()).thenReturn(true);
//      when(riskCenterService.checkArvForFraud(requestDTOWithoutContractId.getMerchantId(), requestDTOWithoutContractId.getCnpj(), ArvFraudCheckChannel.FRONT_VENDAS, ALTER_RECURRING, DAILY))
//          .thenReturn(ArvFraudVerificationResponse.builder().verificationStatus(REJECTED).build());
//
//      verifyNoInteractions(recurringAnticipationRepository);
//      assertThrows(ArvFraudException.class,  () -> recurringAnticipationService.createRecurringAnticipation(requestDTOWithoutContractId));
//    }
//
//    @Test
//    void shouldInactivateRecurringAnticipationContractsCheckingForFraud() throws ArvFraudException {
//      activeRecurringAnticipationContractDTO.getRecurringType().setDescription(DAILY.getDescription());
//      List<RecurringAnticipation> activeRecurringAnticipationContracts = List.of(activeRecurringAnticipationContract);
//      List<RecurringAnticipationDTO> activeRecurringAnticipationContractDTO = List.of(this.activeRecurringAnticipationContractDTO);
//
//      when(recurringAnticipationRepository.findAllByCnpjAndMerchantIdAndActiveContract(CNPJ, MERCHANT_ID, true))
//          .thenReturn(activeRecurringAnticipationContracts);
//      when(recurringAnticipationMapperMock.entitiesToDTOsToList(activeRecurringAnticipationContracts)).thenReturn(activeRecurringAnticipationContractDTO);
//
//      when(channelService.findChannelById(this.activeRecurringAnticipationContractDTO.getChannel().getId())).thenReturn(channel);
//
//      when(recurringAnticipationMapperMock.dtosToEntities(any())).thenReturn(activeRecurringAnticipationContracts);
//      when(recurringAnticipationRepository.saveAll(activeRecurringAnticipationContracts)).thenReturn(activeRecurringAnticipationContracts);
//      when(merchantArvProperties.getRiskCenterIntegrationEnabled()).thenReturn(true);
//      when(riskCenterService.checkArvForFraud(MERCHANT_ID, CNPJ, ArvFraudCheckChannel.FRONT_VENDAS, DEACTIVATE_RECURRING, DAILY))
//          .thenReturn(arvFraudVerificationResponse);
//      when(channelMapper.entityToDTO(any())).thenReturn(channelDTO);
//
//      List<RecurringAnticipationDTO> dto = recurringAnticipationService.inactivateRecurringAnticipationContracts(CNPJ, MERCHANT_ID, true, auditInfo);
//
//      assertNotNull(dto, "Should not be null!");
//      assertFalse(dto.isEmpty(), "Should not be empty!");
//
//      verify(recurringAnticipationMapperMock).dtosToEntities(activeRecurringAnticipationContractDTO);
//      verify(recurringAnticipationRepository).saveAll(activeRecurringAnticipationContracts);
//    }
//
//    @Test
//    void shouldNotInactivateRecurringAnticipationWhenFraudIsDetected() throws ArvFraudException {
//      activeRecurringAnticipationContractDTO.getRecurringType().setDescription(DAILY.getDescription());
//      List<RecurringAnticipation> activeRecurringAnticipationContracts = List.of(activeRecurringAnticipationContract);
//      List<RecurringAnticipationDTO> activeRecurringAnticipationContractDTO = List.of(this.activeRecurringAnticipationContractDTO);
//
//      when(recurringAnticipationRepository.findAllByCnpjAndMerchantIdAndActiveContract(CNPJ, MERCHANT_ID, true))
//          .thenReturn(activeRecurringAnticipationContracts);
//      when(recurringAnticipationMapperMock.entitiesToDTOsToList(activeRecurringAnticipationContracts)).thenReturn(activeRecurringAnticipationContractDTO);
//
//      when(channelService.findChannelById(this.activeRecurringAnticipationContractDTO.getChannel().getId())).thenReturn(channel);
//
//      when(merchantArvProperties.getRiskCenterIntegrationEnabled()).thenReturn(true);
//      when(riskCenterService.checkArvForFraud(MERCHANT_ID, CNPJ, ArvFraudCheckChannel.FRONT_VENDAS, DEACTIVATE_RECURRING, DAILY))
//          .thenReturn(ArvFraudVerificationResponse.builder().verificationStatus(REJECTED).build());
//
//      verifyNoInteractions(recurringAnticipationRepository);
//      assertThrows(ArvFraudException.class,  () -> recurringAnticipationService.inactivateRecurringAnticipationContracts(CNPJ, MERCHANT_ID, true, auditInfo));
//    }
//
//    @Test
//    void shouldInactivateRecurringAnticipationContractsWithoutCheckingForFraud() throws ArvFraudException {
//      activeRecurringAnticipationContractDTO.getRecurringType().setDescription(DAILY.getDescription());
//      List<RecurringAnticipation> activeRecurringAnticipationContracts = List.of(activeRecurringAnticipationContract);
//      List<RecurringAnticipationDTO> activeRecurringAnticipationContractDTO = List.of(this.activeRecurringAnticipationContractDTO);
//
//      when(recurringAnticipationRepository.findAllByCnpjAndMerchantIdAndActiveContract(CNPJ, MERCHANT_ID, true))
//          .thenReturn(activeRecurringAnticipationContracts);
//      when(recurringAnticipationMapperMock.entitiesToDTOsToList(activeRecurringAnticipationContracts)).thenReturn(activeRecurringAnticipationContractDTO);
//
//      when(channelService.findChannelById(this.activeRecurringAnticipationContractDTO.getChannel().getId())).thenReturn(channel);
//
//      when(recurringAnticipationMapperMock.dtosToEntities(any())).thenReturn(activeRecurringAnticipationContracts);
//      when(recurringAnticipationRepository.saveAll(activeRecurringAnticipationContracts)).thenReturn(activeRecurringAnticipationContracts);
//      when(merchantArvProperties.getRiskCenterIntegrationEnabled()).thenReturn(true);
//      when(channelMapper.entityToDTO(any())).thenReturn(channelDTO);
//
//      List<RecurringAnticipationDTO> dto = recurringAnticipationService.inactivateRecurringAnticipationContracts(CNPJ, MERCHANT_ID, false, auditInfo);
//
//      assertNotNull(dto, "Should not be null!");
//      assertFalse(dto.isEmpty(), "Should not be empty!");
//
//      verifyNoInteractions(riskCenterService);
//      verify(recurringAnticipationMapperMock).dtosToEntities(activeRecurringAnticipationContractDTO);
//      verify(recurringAnticipationRepository).saveAll(activeRecurringAnticipationContracts);
//    }
//  }

}
