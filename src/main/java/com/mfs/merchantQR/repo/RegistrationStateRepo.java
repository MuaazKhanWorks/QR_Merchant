package com.mfs.merchantQR.repo;

import com.mfs.merchantQR.model.RegistrationState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationStateRepo extends JpaRepository<RegistrationState,Long> {
}
