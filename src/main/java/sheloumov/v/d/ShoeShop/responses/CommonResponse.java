package sheloumov.v.d.ShoeShop.responses;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class CommonResponse<C extends Object> {

    private int page;
    private int per_page;

    private int total;
    private int total_pages;

    private List<C> data;

    @Autowired
    public CommonResponse(List<C> data) {
        this.data = data;
    }

    public List<C> getData() {
        return data;
    }

    public void setData(List<C> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
