package com.pingpong.quoteBakery.com.resource;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
public class PageResource {
    @Schema(description = "현재페이지")
    private Integer page;
    @Schema(description = "페이지별 조회할 데이터 건수")
    private Integer sizePerPage;

    /**
     * Get the page request based on the values in this resource.
     *
     * @return the page request
     */
    @Hidden // Hide this operation from Swagger UI
    public PageRequest getPageInfo() {
        if (this.getPage() == null) { this.setPage(1); }
        if (this.getSizePerPage() == null){ this.setSizePerPage(10); }

        return PageRequest.of(this.getPage(), this.getSizePerPage());
    }
}
