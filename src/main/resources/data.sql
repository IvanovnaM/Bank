
INSERT INTO account (id, account_number, balance, beneficiar_name, pin_code) VALUES
                                                                                (1, '1234567890', 7770.00, 'Natalia Sazankova', '1234'),
                                                                                (2, '0987654321', 6000.00, 'Zoya Bublik', '5678');

INSERT INTO transaction (id, account_id, amount, type, timestamp) VALUES
                                                                      (1, 1, 7770.00, 'DEPOSIT', NOW()),
                                                                      (2, 2, 6000.00, 'DEPOSIT', NOW());