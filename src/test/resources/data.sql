-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS invoice;

CREATE TABLE INVOICE (
  customer_id INT NOT NULL,
  invoice_id INT NOT NULL,
  invoice_data VARCHAR(250)
);

ALTER TABLE INVOICE ADD PRIMARY KEY (customer_id, invoice_id);

INSERT INTO invoice (customer_id, invoice_id, invoice_data) VALUES
  (1, 54, '{"time": "19:53","tenderDetails": {"amount": 23.43,"type": "cash"},"storeNumber": "999"}'),
  (2, 55 , '{"time": "12:00","tenderDetails": {"amount": 4.95,"type": "cash"},"storeNumber": "999"}'),
  (2, 56, '{"time": "8:49","tenderDetails": {"amount": 100.12,"type": "credit"},"storeNumber": "999"}')