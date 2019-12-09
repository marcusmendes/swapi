import mongoose from 'mongoose';

export default function (): void {
  const url = process.env.MONGO_URL || '';
  mongoose.connect(url, {
    useNewUrlParser: true,
    useFindAndModify: true,
    useUnifiedTopology: true,
    useCreateIndex: true,
  });
}
