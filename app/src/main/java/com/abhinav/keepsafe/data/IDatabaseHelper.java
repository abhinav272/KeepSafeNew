package com.abhinav.keepsafe.data;

import com.abhinav.keepsafe.pojo.Category;

import java.util.List;

/**
 * Created by Abhinav on 21/05/17.
 */
public interface IDatabaseHelper {

    /** methods for categories
     */
    List<Category> getAllCategories();
    void addCategory(Category category);
}
