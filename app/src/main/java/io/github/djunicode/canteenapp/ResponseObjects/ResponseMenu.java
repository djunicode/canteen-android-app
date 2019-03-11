package io.github.djunicode.canteenapp.ResponseObjects;

public class ResponseMenu {

    private Integer id;
    private Integer category;
    private String category_name;
    private String name;
    private Integer price;
    private Boolean is_available;
    private String preparation_time;
    private String option;

    public Integer getId()
    {
        return id;
    }
    public Integer getCategory()
    {
        return category;
    }
    public String getCategory_name()
    {
        return category_name;
    }
    public String getName()
    {
        return name;
    }
    public Integer getPrice()
    {
        return  price;
    }
    public Boolean getIs_available()
    {
        return is_available;
    }
    public String getPreparation_time()
    {
        return preparation_time;
    }
    public String getOption()
    {
        return option;
    }
}
