import { Service } from './service.model';
import { Part } from './part.model';
import { User } from './user.model';

export interface Booking{ 
    id: number;
    vehicle_number_plate: string;
    vehicle_model: string;
    vehicle_brand: string;
    vehicle_engine: string;
    date: Date;
    status: string;
    comments: string;
    base_service: Service;
    vehicle_type: string;
    customer: User;
    mechanic: User;
    extra_service: Service[];
    total:string;
    parts: Part[];
}
