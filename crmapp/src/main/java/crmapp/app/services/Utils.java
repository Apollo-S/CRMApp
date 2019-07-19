package crmapp.app.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Utils {

    public static <E, R> List<R> convertEntityToDTO(Collection<E> source, Class<R> classDTO) {
        List<R> resultDTO = source.stream()
                .map(item -> {
                    R itemDTO = null;
                    try {
                        itemDTO = classDTO.newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    BeanUtils.copyProperties(item, itemDTO);
                    return itemDTO;
                }).collect(toList());
        return resultDTO;
    }

    @NotNull
    public static String getEntityName(Class<?> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

}
