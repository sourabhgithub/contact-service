package com.solstice.contact.controller;

import static com.solstice.contact.fixtures.ContactControllerFixtures.getContactRequest;
import static com.solstice.contact.fixtures.ContactControllerFixtures.getContactUpdateRequest;
import static com.solstice.contact.fixtures.ContactControllerFixtures.getContacts;
import static com.solstice.contact.fixtures.ContactControllerFixtures.getContactsPage;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.solstice.contact.model.request.ContactRequest;
import com.solstice.contact.model.request.ContactUpdateRequest;
import com.solstice.contact.service.ContactService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ContactControllerTest {
  @Autowired private MockMvc mockMvc;
  @MockBean private ContactService contactService;

  @Autowired private ObjectMapper objectMapper;

  @Test
  public void getContacts_WithNoParameters_whenMethodInvoked_shouldReturnContactPage()
      throws Exception {
    given(this.contactService.getContacts(any(), any(), any(), any(), any(), any()))
        .willReturn(getContactsPage());
    mockMvc
        .perform(get("/contacts"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.content[0].name").value("Test Name"));
  }

  @Test
  public void getContact_givenValidContactId_whenMethodInvoked_shouldReturnContact()
      throws Exception {
    given(this.contactService.getContact(any(Long.class))).willReturn(Optional.of(getContacts()));
    mockMvc
        .perform(get("/contacts/{id}", 1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.name").value("Test Name"));
  }

  @Test
  public void getContact_givenInvalidContactId_whenMethodInvoked_shouldReturnNotFoundException()
      throws Exception {
    given(this.contactService.getContact(any(Long.class))).willReturn(Optional.empty());
    mockMvc.perform(get("/contacts/{id}", -3)).andExpect(status().isNotFound());
  }

  @Test
  public void createContact_givenContactRequest_whenMethodInvoked_shouldReturnNewCreatedContact()
      throws Exception {
    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
    ContactRequest contactRequest = getContactRequest();
    String requestJson = ow.writeValueAsString(contactRequest);

    given(this.contactService.createContact(contactRequest)).willReturn(getContacts());
    mockMvc
        .perform(
            post("/contacts").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
        .andExpect(status().isOk());
  }

  @Test
  public void
      updateContact_givenContactIdandContactUpdateRequest_whenMethodInvoked_shouldReturnUpdatedContact()
          throws Exception {
    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
    ContactUpdateRequest contactUpdateRequest = getContactUpdateRequest();
    String requestJson = ow.writeValueAsString(contactUpdateRequest);

    given(this.contactService.updateContact(anyLong(), any()))
        .willReturn(Optional.of(getContacts()));
    mockMvc
        .perform(
            put("/contacts/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
        .andExpect(status().isOk());
  }
  
  @Test
  public void
      updateContact_givenInvalidContactIdandContactUpdateRequest_whenMethodInvoked_shouldReturnNotFoundException()
          throws Exception {
    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
    ContactUpdateRequest contactUpdateRequest = getContactUpdateRequest();
    String requestJson = ow.writeValueAsString(contactUpdateRequest);

    given(this.contactService.updateContact(anyLong(), any()))
        .willReturn(Optional.empty());
    mockMvc
        .perform(
            put("/contacts/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
        .andExpect(status().isNotFound());
  }
  
  @Test
  public void deleteContact_givenValidContactId_whenMethodInvoked_shouldDeleteContactAndReturnNoContent()
      throws Exception {
    doNothing().when(this.contactService).deleteContact(any(Long.class));
    mockMvc
        .perform(delete("/contacts/{id}", 1))
        .andExpect(status().isNoContent());
  }

  @Test
  public void deleteContact_givenInvalidContactId_whenMethodInvoked_shouldReturnNotFoundException()
      throws Exception {
    doThrow(new ResourceNotFoundException()).when(this.contactService).deleteContact(any(Long.class));
    mockMvc.perform(delete("/contacts/{id}", -2)).andExpect(status().isNotFound());
  }
}
