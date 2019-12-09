import mongoose from 'mongoose';

const mongoConnect = (): void => {
  const url = process.env.MONGO_URL || '';
  mongoose.connect(url, {
    useNewUrlParser: true,
    useFindAndModify: true,
    useUnifiedTopology: true,
    useCreateIndex: true,
  });
};

export default mongoConnect();
