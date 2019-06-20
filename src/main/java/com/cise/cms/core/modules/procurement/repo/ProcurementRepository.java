package com.cise.cms.core.modules.procurement.repo;

import com.cise.cms.core.modules.procurement.models.Procurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProcurementRepository extends CrudRepository<Procurement, Long> {

    Page<Procurement> findAll(Pageable pageable);

    Page<Procurement> findByStatusProc(long statusProc, Pageable pageable);

    @Query(
            value = "SELECT a.* FROM prc_request a LEFT JOIN prc_karyawan b on a.nik_req = b.nik LEFT JOIN prc_department c on b.department_id = c.department_id LEFT JOIN prc_product d on a.product_id = d.product_id WHERE d.product_name LIKE ?1 OR b.nama LIKE ?2 OR department_name LIKE ?3 \n-- #pageable\n",
            countQuery = "SELECT COUNT(1) FROM prc_request a LEFT JOIN prc_karyawan b on a.nik_req = b.nik LEFT JOIN prc_department c on b.department_id = c.department_id LEFT JOIN prc_product d on a.product_id = d.product_id WHERE d.product_name LIKE ?1 OR b.nama LIKE ?2 OR department_name LIKE ?3",
            nativeQuery = true)
    Page<Procurement> findByConditionsPageable(String key, String key1, String key2, Pageable pageable);

    @Query(
            value = "SELECT a.* FROM prc_request a LEFT JOIN prc_karyawan b on a.nik_req = b.nik LEFT JOIN prc_department c on b.department_id = c.department_id LEFT JOIN prc_product d on a.product_id = d.product_id WHERE a.status_proc=?1 AND (d.product_name LIKE ?2 OR b.nama LIKE ?3 OR department_name LIKE ?4) \n-- #pageable\n",
            countQuery = "SELECT COUNT(1) FROM prc_request a LEFT JOIN prc_karyawan b on a.nik_req = b.nik LEFT JOIN prc_department c on b.department_id = c.department_id LEFT JOIN prc_product d on a.product_id = d.product_id WHERE a.status_proc=?1 AND (d.product_name LIKE ?2 OR b.nama LIKE ?3 OR department_name LIKE ?4)",
            nativeQuery = true)
    Page<Procurement> findByConditionsPageable(long statusProc, String key, String key1, String key2, Pageable pageable);

    @Query("SELECT COUNT(u) FROM Procurement u WHERE u.statusProc=?1")
    Long countByStatus(long status);

}
