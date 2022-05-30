package woowacourse.auth.dao;

import woowacourse.auth.entity.CustomerEntity;

public interface CustomerDao {
    int save(CustomerEntity customerEntity);

    CustomerEntity findById(int id);

    CustomerEntity findByEmail(String email);

    void update(CustomerEntity customerEntity);

    void delete(int id);
}