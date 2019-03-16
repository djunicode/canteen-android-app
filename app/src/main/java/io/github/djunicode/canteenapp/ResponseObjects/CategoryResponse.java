package io.github.djunicode.canteenapp.ResponseObjects;



public class CategoryResponse
{
    //@SerializedName("id")
    //@Expose
    private Integer id;
    //@SerializedName("name")
    //0@Expose
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
