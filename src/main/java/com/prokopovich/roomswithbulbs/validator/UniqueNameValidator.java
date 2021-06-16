package com.prokopovich.roomswithbulbs.validator;

import com.prokopovich.roomswithbulbs.dao.RoomDao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    private final RoomDao roomDao;

    public UniqueNameValidator(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public void initialize(UniqueName paramA) { }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext ctx) {
        if(name == null) {
            return false;
        }
        if(roomDao.findByName(name) == null) {
            return true;
        } else {
            return false;
        }
    }
}
