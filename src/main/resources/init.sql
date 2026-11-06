create table if not exists equipment_suppliers (
    id bigint primary key,
    name varchar(100) not null,
    phone varchar(20),
    email varchar(100),
    constraint uq_supplier_email unique (email)
);

create table if not exists sports_equipment (
    id bigint primary key,
    name varchar(100) not null,
    equipment_category varchar(100) not null,
    equipment_type varchar(50) not null,
    quantity int not null,
    price decimal(10,2) not null,
    manufacturer varchar(100) not null,
    max_speed decimal(6,2),
    max_load decimal(8,2),
    max_incline decimal(5,2),
    supplier_id bigint,

    constraint chk_quantity check (quantity >= 0),
    constraint chk_price check (price >= 0),

    constraint chk_equipment_type check (
        equipment_type in (
            'CARDIO',
            'STRENGTH',
            'TREADMILL'
        )
    ),

    constraint fk_equipment_supplier
        foreign key (supplier_id)
        references equipment_suppliers(id)
        on delete restrict
        on update cascade
);
