package course.springdata.gameshop.config.domain.dtos;

public class DeleteGameDto {
    private Long id;

    public DeleteGameDto(Long id) {
        this.id = id;
    }

    public DeleteGameDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
