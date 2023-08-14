-- Insert data into the Users table
INSERT INTO physician (username, password, role) VALUES ('mudit', 'hashed_password_mudit', 'medical staff');
INSERT INTO physician (username, password, role) VALUES ('jaimaica', 'hashed_password_jaimaica', 'medical staff');
INSERT INTO physician (username, password, role) VALUES ('jaydenn', 'hashed_password_jaydenn', 'technician');
INSERT INTO physician (username, password, role) VALUES ('viraj', 'hashed_password_viraj', 'admin');

-- Insert data into the Patients table
INSERT INTO patient (first_name, last_name, dob, gender, contact_number) VALUES ('Patient A', 'Last A', '1990-05-20', 'Female', '123-456-7890');
INSERT INTO patient (first_name, last_name, dob, gender, contact_number) VALUES ('Patient B', 'Last B', '1985-10-15', 'Male', '987-654-3210');

-- Insert data into the Appointments table
INSERT INTO appointment (patient_id, appointment_datetime, status) VALUES (1, '2023-08-15 10:00:00', 'confirmed');
INSERT INTO appointment (patient_id, appointment_datetime, status) VALUES (2, '2023-08-16 14:30:00', 'pending');

-- Insert data into the Tests table
INSERT INTO test (test_name) VALUES ('Blood Test');
INSERT INTO test (test_name) VALUES ('X-ray');
INSERT INTO test (test_name) VALUES ('Urine Analysis');

-- Insert data into the TestOrders table
INSERT INTO test_order (patient_id, physician_id, test_id, order_datetime, status) VALUES (1, 1, 1, '2023-08-15 11:00:00', 'pending');
INSERT INTO test_order (patient_id, physician_id, test_id, order_datetime, status) VALUES (2, 2, 2, '2023-08-16 15:00:00', 'in-progress');

-- Insert data into the TestResults table
INSERT INTO test_result (test_order_id, technician_id, result_text) VALUES (1, 3, 'Blood test result');
INSERT INTO test_result (test_order_id, technician_id, result_text) VALUES (2, 3, 'X-ray report');
