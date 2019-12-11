import { Schema, Document, model } from 'mongoose';

interface PlanetInterface extends Document {
  name: string,
  climate: string,
  terrain: string,
  amountFilms?: number
}

const Planet = new Schema({
  name: {
    type: String,
    required: true,
  },
  climate: {
    type: String,
    required: true,
  },
  terrain: {
    type: String,
    required: true,
  },
  amountFilms: Number,
}, {
  timestamps: true,
});

Planet.index({ name: 'text' });

export default model<PlanetInterface>('Planet', Planet);
