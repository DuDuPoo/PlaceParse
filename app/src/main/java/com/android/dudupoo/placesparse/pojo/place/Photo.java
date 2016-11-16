package com.android.dudupoo.placesparse.pojo.place;

public class Photo
{
    private int width;
    private String photo_reference;

    public Photo(int width, String photo_reference)
    {
        this.width = width;
        this.photo_reference = photo_reference;
    }

    @Override
    public String toString()
    {
        return "Photo{" +
                "width=" + width +
                ", photo_reference='" + photo_reference + '\'' +
                '}';
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public String getPhoto_reference()
    {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference)
    {
        this.photo_reference = photo_reference;
    }
}
