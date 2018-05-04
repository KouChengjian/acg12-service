package com.acg12.service.person;

import com.acg12.entity.po.person.PersonEntity;
import com.acg12.utils.pagination.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
public interface PersonService {

    List<PersonEntity> queryByPersonList(PageInfo pageInfo , String type, int gender, int bloodtype, String birthday);

    PersonEntity queryByPersonIdJoinDetail(int personId);
}
