package com.hutech.DAMH;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public enum Role {
    ADMIN(1), // Vai trò quản trị viên, có quyền cao nhất trong hệ thống.
    USER(2), // Vai trò người dùng bình thường, có quyền hạn giới hạn.
    EMPLOYEE(3); // Vai trò quản trị viên, có quyền cao nhất trong hệ thống.
    public final long value; // Biến này lưu giá trị số tương ứng với mỗi vai trò.

}
