import java.util.Objects;

public abstract class BaseEntity {
    private long id;
    private String name;

    public BaseEntity(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name= name;
    }

    @Override public boolean equals(Object obj){
        if (this == obj){
            return true;
        }

        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        BaseEntity that = (BaseEntity) obj;
        return id == that.id;
    }

    @Override 
    public int hashCode(){
        return Objects.hash(id);
    }

}
