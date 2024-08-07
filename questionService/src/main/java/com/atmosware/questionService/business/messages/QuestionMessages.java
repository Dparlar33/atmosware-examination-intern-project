package com.atmosware.questionService.business.messages;

public class QuestionMessages {
    public static String QUESTION_NOT_FOUND = "QUESTION_NOT_FOUND";
    public static String INVALID_REQUEST_ROLE = "INVALID_REQUEST_ROLE";
    public static String QUESTION_HAS_TO_INCLUDE_ONE_CORRECT_OPTION_OR_CHANGE_ONE_OPTION_TO_CORRECT = "QUESTION_HAS_TO_INCLUDE_ONE_CORRECT_OPTION_PLEASE_ADD_ONE_CORRECT_OPTION_OR_CHANGE_ONE_OPTION_TO_CORRECT";
    public static String DESCRIPTION_AND_IMAGE_URL_CANNOT_BE_NULL_AT_THE_SAME_QUESTION = "DESCRIPTION_AND_IMAGE_URL_CANNOT_BE_NULL_AT_THE_SAME_QUESTION";
    public static String THIS_QUESTION_IS_CURRENTLY_IN_USE_CANNOT_BE_UPDATED = "THIS_QUESTION_IS_CURRENTLY_IN_USE_CANNOT_BE_UPDATED";
    public static String OPTION_COUNTS_HAVE_TO_BE_LOWER_THAN_FIVE = "OPTION_COUNTS_HAVE_TO_BE_LOWER_THAN_FIVE_PLEASE_DELETE_OPTION";
    public static String OPTION_COUNTS_HAVE_TO_BE_HIGHER_THAN_TWO = "OPTION_COUNTS_HAVE_TO_BE_HIGHER_THAN_TWO_PLEASE_DELETE_OPTION";

}
