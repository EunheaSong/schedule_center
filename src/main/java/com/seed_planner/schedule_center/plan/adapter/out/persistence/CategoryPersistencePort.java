package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.CategoryRes;
import com.seed_planner.schedule_center.plan.application.port.out.CategoryInfoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CategoryPersistencePort implements CategoryInfoPort {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryRes> getBasicInfoAllByMemberId(String memberId) {
        return categoryRepository.getBasicInfoByMemberId(memberId);
    }
}
