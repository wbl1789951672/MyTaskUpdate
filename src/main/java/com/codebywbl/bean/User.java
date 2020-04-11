package com.codebywbl.bean;/*
    @author:bl
    @date:2020/04/01 19:47
    @description:
*/

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "myCollection")
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class User {
    private String id;
    private String name;
    private int age;
    private String address;
    private String[] hobby;
    private Description description;


}
