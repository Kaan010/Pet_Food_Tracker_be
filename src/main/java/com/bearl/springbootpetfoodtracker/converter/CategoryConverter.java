/*package com.bearl.springbootpetfoodtracker.converter;


import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryConverter {

    CategoryConverter INSTANCE = Mappers.getMapper(CategoryConverter.class);

    @Mapping(target = "parentLevel", source = "parentLevel.id")
    CategoryDto convertKategoriToKategoriDto(Category kategori);

    @Mapping(target = "parentLevel", source = "parentLevel.id")
    List<CategoryDto> convertAllKategoriListToKategoriDtoList(List<Category> kategoriList);

    @Mapping(target = "parentLevel.id", source = "parentLevel")
    Category convertKategoriDtoToKategori(CategoryDto kategoriDto);

    @AfterMapping
    default void setNulls(@MappingTarget Category kategori, CategoryDto kategoriDto){
        if (kategoriDto.getParentLevel() == null){
            kategori.setParentLevel(null);
        }
    }
}
*/