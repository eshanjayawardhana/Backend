package com.bit.backend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Matches the previous institute API envelope: { "data": { "dataList": [...] } }
 */
public class ApiListResponse<T> {
    private ApiData<T> data;

    public ApiListResponse() {
    }

    public ApiListResponse(List<T> dataList) {
        this.data = new ApiData<>(dataList);
    }

    public static <T> ApiListResponse<T> of(List<T> dataList) {
        return new ApiListResponse<>(dataList);
    }

    public static <T> ApiListResponse<T> ofOne(T item) {
        return new ApiListResponse<>(List.of(item));
    }

    public static <T> ApiListResponse<T> ofPage(List<T> dataList, int page, int size, long totalElements, int totalPages) {
        ApiListResponse<T> response = new ApiListResponse<>(dataList);
        response.getData().setPage(page);
        response.getData().setSize(size);
        response.getData().setTotalElements(totalElements);
        response.getData().setTotalPages(totalPages);
        return response;
    }

    public ApiData<T> getData() {
        return data;
    }

    public void setData(ApiData<T> data) {
        this.data = data;
    }

    public static class ApiData<T> {
        private List<T> dataList;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer page;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer size;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Long totalElements;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer totalPages;

        public ApiData() {
        }

        public ApiData(List<T> dataList) {
            this.dataList = dataList;
        }

        public List<T> getDataList() {
            return dataList;
        }

        public void setDataList(List<T> dataList) {
            this.dataList = dataList;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Long getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(Long totalElements) {
            this.totalElements = totalElements;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }
    }
}
