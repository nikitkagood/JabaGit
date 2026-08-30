package Homework3;

public class ComplexPicture
{
    private String pictureHeader;
    private String pictureData;

    public ComplexPicture(SimplePicture simplePicture)
    {
        pictureHeader = makeHeader();
        pictureData = simplePicture.getSimplePictureData();
    }

    private String makeHeader()
    {
        return "ComplexPicHeader";
    }

    public String getPictureHeader() {
        return pictureHeader;
    }

    public String getPictureData() {
        return pictureData;
    }
}
