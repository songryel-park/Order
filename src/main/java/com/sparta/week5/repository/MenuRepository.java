package com.sparta.week5.repository;

import com.sparta.week5.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("Select this_.menus from Restaurant this_ where this_.id=:id")
    public List<Menu> getMenuByRestaurantId(@Param("id") long id);

    @Query("Select this_ from Menu this_ where this_.id in (:ids)")
    public List<Menu> getMenusByIdList(@Param("ids") List<Long> ids);
}
