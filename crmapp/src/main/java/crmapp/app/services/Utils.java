package crmapp.app.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Utils {

    public static <T> List<T> convertEntityToDTO(@NotNull Collection<?> source, Class<T> targetDTO) {
        return source.stream()
                .map(item -> {
                    T itemDTO = null;
                    try {
                        itemDTO = targetDTO.newInstance();
                        BeanUtils.copyProperties(item, itemDTO);
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return itemDTO;
                }).collect(toList());
    }

    public static <E, T> T convertEntityToDTO(E source, Class<T> targetDTO) {
        T entityDTO = null;
        try {
            entityDTO = targetDTO.newInstance();
            BeanUtils.copyProperties(source, entityDTO);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return entityDTO;
    }

    public static <E, T> T convertDTOToEntity(E sourceDTO, Class<T> target) {
        return convertEntityToDTO(sourceDTO, target);
    }

    @NotNull
    public static String getEntityName(Class<?> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

}
