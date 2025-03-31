package com.ljk.boot.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiResponseDTO {
    private int page;
    private int limit;
    private String status;
    private int totalPage;
    private List<UserDTO> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<UserDTO> getUserDTO() {
        return data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setUserDTO(List<UserDTO> data) {
        this.data = data;
    }

    public static ApiResponseDTO toDTO(List<UserDTO> data,int totalPage, String status,int page, int limit){
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        apiResponseDTO.setPage(page);
        apiResponseDTO.setLimit(limit);
        apiResponseDTO.setStatus(status);
        apiResponseDTO.setTotalPage(totalPage);
        apiResponseDTO.setUserDTO(data);
        return apiResponseDTO;
    }
}
