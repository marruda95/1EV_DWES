package com.sanvalero.AAMaria_Arruda.service;

import org.springframework.web.multipart.MultipartFile;

/** SERVICE PARA GESTIÓN DE FICHEROS **/
public interface FileService {
    void uploadFile(MultipartFile file);
}
