package pl.dave.project.webdietserver.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.dave.project.webdietserver.dto.NameAndGuid;
import pl.dave.project.webdietserver.dto.recipe.RecipeListRecord;
import pl.dave.project.webdietserver.dto.recipe.RecipeRequest;
import pl.dave.project.webdietserver.entity.Recipe;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, UserMapper.class})
public abstract class RecipeMapper {

    @Mapping(target = "creationTimestamp", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "totalKcal", ignore = true)
    public abstract Recipe toEntity(RecipeRequest request);

    @AfterMapping
    void setGuid(RecipeRequest request, @MappingTarget Recipe recipe) {
        if (!StringUtils.isEmpty(request.getGuid())) {
            recipe.setGuid(request.getGuid());
        }
    }

    public abstract RecipeListRecord toListRecord(Recipe recipe);

    public abstract List<RecipeListRecord> toListRecords(List<Recipe> recipes);

    @Mapping(target = "guid", ignore = true)
    @Mapping(target = "products", ignore = true)
    public abstract Recipe update(@MappingTarget Recipe target, Recipe source);

    public abstract NameAndGuid toNameAndGuid(Recipe recipe);

    public abstract RecipeRequest toRequest(Recipe recipes);

    public abstract List<RecipeRequest> toRequests(List<Recipe> recipes);
}
