package in.astro.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with name %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    public ResourceNotFoundException(String resourceName,String fieldName){
        super(String.format("%s not found with name %s",resourceName,fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }
}
