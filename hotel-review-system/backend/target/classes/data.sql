INSERT INTO hotels (name, location, description, image_url) VALUES
('The Grand Palace', 'Hyderabad, India', 'A luxurious 5-star hotel in the heart of the city with world-class amenities.', 'https://images.unsplash.com/photo-1566073771259-6a8506099945?w=600'),
('Seaside Retreat Resort', 'Goa, India', 'Beachfront resort offering stunning ocean views and relaxing spa services.', 'https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?w=600'),
('Mountain View Lodge', 'Manali, India', 'Cozy lodge nestled in the mountains, perfect for a peaceful getaway.', 'https://images.unsplash.com/photo-1571003123894-1f0594d2b5d9?w=600'),
('Urban Business Hotel', 'Bengaluru, India', 'Modern hotel designed for business travelers with high-speed WiFi and conference rooms.', 'https://images.unsplash.com/photo-1445019980597-93fa8acb246c?w=600');

INSERT INTO reviews (reviewer_name, rating, comment, created_at, hotel_id) VALUES
('Ananya Rao', 5, 'Absolutely wonderful stay! The staff was very courteous and the rooms were spotless.', CURRENT_TIMESTAMP, 1),
('Vikram Singh', 4, 'Great location and good food, but check-in took a while.', CURRENT_TIMESTAMP, 1),
('Priya Nair', 5, 'The beach view from our room was breathtaking. Will definitely come back!', CURRENT_TIMESTAMP, 2),
('Rahul Mehta', 3, 'Decent stay, but the AC was a bit noisy at night.', CURRENT_TIMESTAMP, 2),
('Sneha Reddy', 5, 'Perfect quiet getaway with amazing mountain views.', CURRENT_TIMESTAMP, 3),
('Arjun Kapoor', 4, 'Clean rooms and friendly staff. Good for a short business trip.', CURRENT_TIMESTAMP, 4);
