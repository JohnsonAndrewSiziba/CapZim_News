package com.capzim.news.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 * Date: 14/9/2022
 * Time: 11:32
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileModel {
    private byte[] fileData;
    private String fileName;
    private String contentType;
}
