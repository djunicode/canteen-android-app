package io.github.djunicode.canteenapp.RequestObjects;

public class GetMenu {
    private Integer id;
    private Integer category;
    private String name;
    private Integer price;
    private Boolean is_available;
    private String preparation_time;
    private String option;

    public GetMenu(Integer id,Integer category,String name,Integer price,Boolean is_available,String preparation_time,String option)
    {
        this.id=id;
        this.category=category;
        this.name=name;
        this.price=price;
        this.is_available=is_available;
        this.preparation_time=preparation_time;
        this.option=option;

    }

}
