-- Insert data into the Users table
INSERT INTO medical_user (username, password, role) VALUES ('mudit', '1234', 'staff');
INSERT INTO medical_user (username, password, role) VALUES ('jaimaica', '1234', 'physician');
INSERT INTO medical_user (username, password, role) VALUES ('jaydenn', '1234', 'technician');
INSERT INTO medical_user (username, password, role) VALUES ('viraj', '1234', 'admin');

-- Insert data into the Patients table
INSERT INTO patient (first_name, last_name, dob, gender, contact_number) VALUES ('John', 'Smith', '1990-05-20', 'Female', '123-456-7890');
INSERT INTO patient (first_name, last_name, dob, gender, contact_number) VALUES ('Jane', 'William', '1985-10-15', 'Male', '987-654-3210');

-- Insert data into the Appointments table
INSERT INTO appointment (patient_id, appointment_datetime, status) VALUES (1, '2023-08-15 10:00:00', 'confirmed');
INSERT INTO appointment (patient_id, appointment_datetime, status) VALUES (2, '2023-08-16 14:30:00', 'pending');

-- Insert data into the Tests table
INSERT INTO test (test_name) VALUES ('Blood Test');
INSERT INTO test (test_name) VALUES ('X-ray');
INSERT INTO test (test_name) VALUES ('Urine Analysis');
INSERT INTO test (test_name) VALUES ('Lipid Panel');
INSERT INTO test (test_name) VALUES ('Fasting Glucose');
INSERT INTO test (test_name) VALUES ('Thyroid Function');
INSERT INTO test (test_name) VALUES ('Kidney Function');
INSERT INTO test (test_name) VALUES ('Imaging Tests');
INSERT INTO test (test_name) VALUES ('Genetic Tests');
INSERT INTO test (test_name) VALUES ('Biopsy');



-- Insert data into the TestOrders table
INSERT INTO test_order (patient_id, medical_user_id, test_id, order_datetime, status) VALUES (1, 1, 1, '2023-08-15 11:00:00', 'pending');
INSERT INTO test_order (patient_id, medical_user_id, test_id, order_datetime, status) VALUES (2, 2, 2, '2023-08-16 15:00:00', 'pending');

-- Insert data into the TestResults table
INSERT INTO test_result (test_order_id, technician_id, result_text) VALUES (1, 3, 'The blood test was conducted to assess various aspects of the patient''s blood chemistry and cell counts.');
INSERT INTO test_result (test_order_id, technician_id, result_text) VALUES (2, 3, 'The X-ray examination was performed on the patient''s chest area to assess the condition of the lungs and heart. No signs of abnormalities, infiltrates, or fractures are observed.');
