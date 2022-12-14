package cz.upce.nnpro_stk_backend.dtos;

import javax.validation.constraints.NotNull;

public class BranchOfficeIdUserIdDto {
    @NotNull(message = "User id is mandatory.")
    Long userId;
    @NotNull(message = "Branch office id is mandatory.")
    Long branchOfficeId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBranchOfficeId() {
        return branchOfficeId;
    }

    public void setBranchOfficeId(Long branchOfficeId) {
        this.branchOfficeId = branchOfficeId;
    }
}
